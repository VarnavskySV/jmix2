package com.company.space.app.services;

import com.company.space.entity.CustomerGrade;
import com.company.space.entity.Discounts;
import com.company.space.entity.Waybill;
import com.company.space.entity.WaybillItem;
import io.jmix.core.DataManager;
import io.jmix.core.Id;
import io.jmix.core.event.EntityChangedEvent;
import io.jmix.core.event.EntitySavingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Component
public class WaybillItemListener {

    @Autowired
    DataManager dataManager;

    @Autowired
    WaybillItemService waybillItemService;
    @EventListener
    public void onWaybillItemSaving(EntitySavingEvent<WaybillItem> event){

        WaybillItem waybillItem = event.getEntity();
        System.out.println(waybillItem.getId());

    }

    @EventListener
    public void onWaybillItemListener(@NotNull EntityChangedEvent<WaybillItem> event) {

        Waybill waybill;
        WaybillItem waybillItem;
        if (event.getType() == EntityChangedEvent.Type.DELETED) {

            Id<Object> id = event.getChanges().getOldReferenceId("waybill");

            waybill = (Waybill) dataManager.load(id).optional().orElse(null);
            if (waybill == null){
                return;
            }

            //Double weight = event.getChanges().getOldValue("weight");

            calculateTotalWeightFields(waybill, waybill.getItems(), -1);

            // Нужно пересчитать номера при удалении.
            recalcNumber(waybill.getItems());

        } else {

            waybillItem = dataManager.load(event.getEntityId()).one();

            waybillItemService.calcChargeField(waybillItem);

            waybill = waybillItem.getWaybill();

            calculateTotalWeightFields(waybill, waybill.getItems(), 1);

        }


        calculateTotalChargeFields(waybill, waybill.getItems());

        dataManager.save(waybill);

    }

    public void calculateTotalWeightFields(Waybill waybill,List<WaybillItem> waybillItems, int direction) {


        Double weigth = waybillItems.stream()
                .mapToDouble(item -> item.getWeight() == null ? 0 : item.getWeight()).sum();

        waybill.setTotalWeight(weigth);

        return;

    }

    public void calculateTotalChargeFields(Waybill waybill, List<WaybillItem> waybillItems) {


        BigDecimal charge = waybillItems.stream()
                .filter(item -> item.getCharge() != null)
                .map(WaybillItem::getCharge)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (waybill.getShipper() != null) {
            CustomerGrade customerGrade = waybill.getShipper().getGrade();
            if (customerGrade != null) {
                Discounts discounts = dataManager.load(Discounts.class)
                        .query("select d from sp_Discounts d where d.grade = :grade")
                        .parameter("grade", customerGrade)
                        .optional().orElse(null);
                if (discounts != null && !discounts.getValue().equals(BigDecimal.ZERO)) {

                    charge = charge.subtract((discounts.getValue().compareTo(BigDecimal.ZERO) > 0 ?
                            discounts.getValue() :
                            discounts.getValue().multiply(BigDecimal.valueOf(-1))).multiply(charge));

                }
            }
        }

        waybill.setTotalCharge(charge);

        return;

    }

    public void swapNumber(WaybillItem waybillItem, int direction) {
        Integer number = waybillItem.getNumber() + direction;

        WaybillItem swapItem = dataManager.load(WaybillItem.class)
                .query("select i from sp_WaybillItem i where i.waybill = :waybill and i.number = :number")
                .parameter("waybill", waybillItem.getWaybill())
                .parameter("number", number)
                .optional().orElse(null);

        if (swapItem == null){

            return ;

        }

        swapItem.setNumber(waybillItem.getNumber());
        waybillItem.setNumber(number);

        dataManager.save(waybillItem, swapItem);

        return;

    }

    public void recalcNumber(List<WaybillItem> waybillItems){

        List<WaybillItem> sorted = waybillItems.stream()
                .sorted((o1, o2)->o1.getNumber().compareTo(o2.getNumber()))
                .collect(Collectors.toList());

        AtomicReference<Integer> number = new AtomicReference<>(1);
        sorted.stream().forEach(item->{
            item.setNumber(number.get());
            number.getAndSet(number.get() + 1);
            dataManager.save(item);
        });

    }

}
