package com.company.space.screen.waybill;

import com.company.space.app.services.WaybillItemService;
import com.company.space.entity.Carrier;
import com.company.space.entity.Customer;
import com.company.space.entity.Individual;
import com.company.space.entity.Waybill;
import com.company.space.entity.WaybillItem;
import com.company.space.screen.carrier.CarrierBrowse;
import io.jmix.ui.RemoveOperation;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.action.Action;
import io.jmix.ui.action.entitypicker.EntityLookupAction;
import io.jmix.ui.component.EntityComboBox;
import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.component.RadioButtonGroup;
import io.jmix.ui.component.TextField;
import io.jmix.ui.model.CollectionChangeType;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionPropertyContainer;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.EditedEntityContainer;
import io.jmix.ui.screen.Install;
import io.jmix.ui.screen.StandardEditor;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.Target;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings({"rawtypes", "ConstantConditions"})
@UiController("sp_Waybill.edit")
@UiDescriptor("waybill-edit.xml")
@EditedEntityContainer("waybillDc")
public class WaybillEdit extends StandardEditor<Waybill> {

    @Autowired
    private RadioButtonGroup<Integer> shipperRadioField;

    @Autowired
    private RadioButtonGroup<Integer> consigneeRadioField;

    @Autowired
    private EntityComboBox<Customer> shipperField;


    @Autowired
    private CollectionContainer<Customer> companyDc;

    @Autowired
    private CollectionContainer<Customer> individualDc;

    @Autowired
    private ScreenBuilders screenBuilders;

    @Autowired
    private InstanceContainer<Waybill> waybillDc;

    @Autowired
    private CollectionPropertyContainer<WaybillItem> itemsDc;
    @Autowired
    private EntityPicker<Carrier> carrierField;

    @Named("consigneeField.entityLookup")
    private EntityLookupAction<Customer> consigneeFieldEntityLookup;

    @Autowired
    private TextField<Double> totalWeightField;

    @Autowired
    private TextField<BigDecimal> totalChargeField;

    @Autowired
    WaybillItemService waybillItemService;

    @Subscribe(id = "waybillDc", target = Target.DATA_CONTAINER)
    public void onWaybillDcItemChange(InstanceContainer.ItemChangeEvent<Waybill> event) {

        Waybill waybill = event.getItem();

        setShipperVisible(waybill);

        setConsigneeVisible(waybill);

    }



    @Subscribe
    public void onInit(InitEvent event) {
        Map<String, Integer> map = new LinkedHashMap<>();

        map.put("Company", 0);
        map.put("Individual", 1);

        shipperRadioField.setOptionsMap(map);

        consigneeRadioField.setOptionsMap(map);

    }

    @Subscribe("shipperRadioField")
    public void onShipperRadioFieldValueChange(@NotNull HasValue.ValueChangeEvent event) {

        int value = (int) event.getValue();

        if (value == 0){

            shipperField.setOptionsContainer(companyDc);

        }else {

            shipperField.setOptionsContainer(individualDc);

        }

    }

    @SuppressWarnings({"ConstantConditions", "rawtypes"})
    @Subscribe("consigneeRadioField")
    public void onConsigneeRadioFieldValueChange(@NotNull HasValue.ValueChangeEvent event) {

        int value = (int) event.getValue();

        if (value == 0){

            consigneeFieldEntityLookup.setScreenId("sp_Company.browse");

        }else {

            consigneeFieldEntityLookup.setScreenId("sp_Individual.browse");

        }

    }

    private void setConsigneeVisible(Waybill waybill){

        int idx = 0;

        if (waybill != null && waybill.getConsignee() != null){

            Customer customer = waybill.getConsignee();

            if (customer.getClass() == Individual.class){

                idx = 1;

            }
        }

        consigneeRadioField.setValue(idx);

    }
    private void setShipperVisible(Waybill waybill){

        int idx = 0;


        if (waybill != null && waybill.getShipper() != null){

            Customer customer = waybill.getShipper();

            if (customer.getClass() == Individual.class){

                idx = 1;

            }
        }

        shipperRadioField.setValue(idx);

    }

    @Subscribe("carrierField.entityLookup")
    public void onCarrierFieldEntityLookup(Action.ActionPerformedEvent event) {

        List<UUID> spaceports = new ArrayList<>();

        Waybill waybill = waybillDc.getItem();

        if (waybill.getDeparturePort() != null){

            spaceports.add(waybill.getDeparturePort().getId());

        }

        if (waybill.getDestinationPort() != null){

            spaceports.add(waybill.getDestinationPort().getId());

        }

        CarrierBrowse carrierScreen = (CarrierBrowse) screenBuilders.lookup(Carrier.class, this)
                .withField(carrierField)
                .build();

        if (spaceports.size()> 0){
            carrierScreen.setPorts(spaceports);
        }

        carrierScreen.show();
    }

    @Subscribe("itemsTable.up")
    public void onItemsTableUp(Action.ActionPerformedEvent event) {

        this.swapNumber( 1);

    }

    @Subscribe("itemsTable.down")
    public void onItemsTableDown(Action.ActionPerformedEvent event) {

        this.swapNumber( -1);

    }

    @Subscribe(id = "itemsDc", target = Target.DATA_CONTAINER)
    public void onItemsDcCollectionChange(CollectionContainer.CollectionChangeEvent<WaybillItem> event) {

        if (event.getChangeType() == CollectionChangeType.REMOVE_ITEMS){

            CollectionContainer<WaybillItem> source = event.getSource();
            List<WaybillItem> items = source.getMutableItems();

            waybillItemService.recalcNumber(items);
            items.forEach(source::replaceItem);

        }

    }

    @Install(to = "itemsTable.edit", subject = "afterCommitHandler")
    private void itemsTableEditAfterCommitHandler(WaybillItem waybillItem) {

        calcTotalField(waybillItem, 1);

    }

    @Install(to = "itemsTable.create", subject = "afterCommitHandler")
    private void itemsTableCreateAfterCommitHandler(WaybillItem waybillItem) {

        calcTotalField(waybillItem, 1);

    }


    @Install(to = "itemsTable.remove", subject = "afterActionPerformedHandler")
    private void itemsTableRemoveAfterActionPerformedHandler(RemoveOperation.AfterActionPerformedEvent<WaybillItem> afterActionPerformedEvent) {

        afterActionPerformedEvent.getItems()
                .forEach(item-> calcTotalField(item, -1));

    }


    private void calcTotalField(WaybillItem waybillItem, int direction){

        List<WaybillItem> items = itemsDc.getItems();
        Waybill waybill = waybillItem.getWaybill();

        waybillItemService.calculateTotalWeightFields(waybill, items, direction);
        totalWeightField.setValue(waybill.getTotalWeight());

        waybillItemService.calculateTotalChargeFields(waybillDc.getItem(), items);
        totalChargeField.setValue(waybillDc.getItem().getTotalCharge());

    }
    private void swapNumber(int direction){

        List<WaybillItem> waybillItems = waybillItemService.swapNumber(itemsDc.getMutableItems(),
                                                    itemsDc.getItem(), direction);

        waybillItems.forEach(item-> itemsDc.replaceItem(item));

    }
}