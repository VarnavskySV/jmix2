package com.company.space.app.services;

import com.company.space.SpaceBaseTest;
import com.company.space.entity.Dimensions;
import com.company.space.entity.Waybill;
import com.company.space.entity.WaybillItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
@ActiveProfiles("test")
class WaybillItemServiceTest extends SpaceBaseTest {

    @Test
    public void checkTotalChargeField(){

        List<WaybillItem> items = new ArrayList<>();

        Waybill waybill = dataManager.create(Waybill.class);

        WaybillItem item1 = dataManager.create(WaybillItem.class);
        Dimensions dim1 = dataManager.create(Dimensions.class);
        dim1.setHeight(Double.valueOf(1));
        dim1.setLength(Double.valueOf(1));
        dim1.setWight(Double.valueOf(1));

        item1.setDim(dim1);
        item1.setWaybill(waybill);

        items.add(item1);

        WaybillItem item2 = dataManager.create(WaybillItem.class);
        Dimensions dim2 = dataManager.create(Dimensions.class);
        dim2.setHeight(Double.valueOf(2));
        dim2.setLength(Double.valueOf(2));
        dim2.setWight(Double.valueOf(2));

        item2.setDim(dim2);
        item2.setWaybill(waybill);
        items.add(item2);

        dataManager.save(waybill);
        dataManager.save(item1, item2);

        entitiesToRemove.add(items);
        entitiesToRemove.add(waybill);

        Waybill saved = dataManager.load(Waybill.class).id(waybill.getId()).one();

        Assertions.assertEquals(9, saved.getTotalCharge());


    }

    @Test
    public void checkTotalWeightField(){

        List<WaybillItem> items = new ArrayList<>();

        Waybill waybill = dataManager.create(Waybill.class);

        WaybillItem item1 = dataManager.create(WaybillItem.class);

        item1.setWeight(1.0);
        item1.setWaybill(waybill);

        items.add(item1);

        WaybillItem item2 = dataManager.create(WaybillItem.class);

        item2.setWeight(1.0);
        item2.setWaybill(waybill);
        items.add(item2);

        dataManager.save(waybill);
        dataManager.save(item1, item2);

        entitiesToRemove.addAll(List.of(item1, item2, waybill));

        Waybill saved = dataManager.load(Waybill.class).id(waybill.getId()).one();

        Assertions.assertEquals(2, saved.getTotalWeight());


    }

}