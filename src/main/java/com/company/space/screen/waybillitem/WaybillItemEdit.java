package com.company.space.screen.waybillitem;

import com.company.space.app.services.WaybillItemService;
import com.company.space.entity.WaybillItem;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.component.TextField;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.EditedEntityContainer;
import io.jmix.ui.screen.StandardEditor;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@UiController("sp_WaybillItem.edit")
@UiDescriptor("waybill-item-edit.xml")
@EditedEntityContainer("waybillItemDc")
public class WaybillItemEdit extends StandardEditor<WaybillItem> {

    @Autowired
    WaybillItemService waybillItemService;

    @Autowired
    private TextField<BigDecimal> chargeField;

    @Autowired
    private InstanceContainer<WaybillItem> waybillItemDc;

    @Subscribe("dimWightField")
    public void onDimWightFieldValueChange(HasValue.ValueChangeEvent<Double> event) {

        calcChargeField();

    }

    @Subscribe("dimHeightField")
    public void onDimHeightFieldValueChange(HasValue.ValueChangeEvent<Double> event) {

        calcChargeField();

    }

    @Subscribe("dimLengthField")
    public void onDimLengthFieldValueChange(HasValue.ValueChangeEvent<Double> event) {

        calcChargeField();

    }

    private void calcChargeField(){

        waybillItemService.calcChargeField(waybillItemDc.getItem());
        chargeField.setValue(waybillItemDc.getItem().getCharge());

    }
    @Subscribe
    public void onInitEntity(InitEntityEvent<WaybillItem> event) {

        WaybillItem waybillItem = event.getEntity();

        waybillItem = waybillItemService.getNextNumber(waybillItem);

        event.getEntity().setNumber(waybillItem.getNumber());

    }

    @Subscribe
    public void onAfterCommitChanges(AfterCommitChangesEvent event) {
        
    }

    
}