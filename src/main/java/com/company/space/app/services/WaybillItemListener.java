package com.company.space.app.services;

import com.company.space.entity.Waybill;
import com.company.space.entity.WaybillItem;
import io.jmix.core.DataManager;
import io.jmix.core.Id;
import io.jmix.core.event.EntityChangedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class WaybillItemListener {

    @Autowired
    DataManager dataManager;

    @Autowired
    WaybillItemService waybillItemService;

    @EventListener
    public void onWaybillItemListener(@NotNull EntityChangedEvent<WaybillItem> event) {

        Waybill waybill;
        WaybillItem waybillItem;
        if (event.getType() == EntityChangedEvent.Type.DELETED) {

            Id<Object> id = event.getChanges().getOldReferenceId("waybill");
            if (id == null){
                return;
            }
            waybill = (Waybill) dataManager.load(id)
                    //.fetchPlan("load-waybill")
                    .optional().orElse(null);

            if (waybill == null){
                return;
            }

            // Нужно пересчитать номера при удалении.
            waybillItemService.recalcNumber(waybill.getItems());

        } else {

            waybillItem = dataManager.load(event.getEntityId()).one();

            waybill = waybillItem.getWaybill();

        }

        waybillItemService.calculateTotalWeightFields(waybill, waybill.getItems(), 1);

        waybillItemService.calculateTotalChargeFields(waybill, waybill.getItems());

        dataManager.save(waybill);

    }


}
