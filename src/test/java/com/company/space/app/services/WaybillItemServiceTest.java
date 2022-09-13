package com.company.space.app.services;

import com.company.space.SpaceBaseTest;
import com.company.space.entity.Dimensions;
import com.company.space.entity.Waybill;
import com.company.space.entity.WaybillItem;
import org.junit.jupiter.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;
@ActiveProfiles("test")
class WaybillItemServiceTest extends SpaceBaseTest {

    @Autowired
    WaybillItemService waybillItemService;

    @Test
    public void checkTotalField(){

        Waybill waybill = dataManager.create(Waybill.class);

        WaybillItem item1 = dataManager.create(WaybillItem.class);

        item1.setWeight(1.0);
        item1.setWaybill(waybill);

        Dimensions dim1 = dataManager.create(Dimensions.class);
        dim1.setHeight(1.0);
        dim1.setLength(1.0);
        dim1.setWight(1.0);

        item1.setDim(dim1);
        waybillItemService.calcChargeField(item1);

        WaybillItem item2 = dataManager.create(WaybillItem.class);

        item2.setWeight(1.0);
        item2.setWaybill(waybill);

        Dimensions dim2 = dataManager.create(Dimensions.class);

        dim2.setHeight(2.0);
        dim2.setLength(2.0);
        dim2.setWight(2.0);

        item2.setDim(dim2);
        waybillItemService.calcChargeField(item2);

        dataManager.save(waybill);
        dataManager.save(item1, item2);

        entitiesToRemove.addAll(List.of(item1, item2, waybill));

        Waybill saved = dataManager.load(Waybill.class).id(waybill.getId()).one();
        // Проверка поля TotalWeight
        Assertions.assertEquals(2, saved.getTotalWeight());
        // Проверка поля TotalCharge
        Assertions.assertEquals(BigDecimal.valueOf(9), saved.getTotalCharge());

    }

    @Test
    public void checkTotalWeightEditField(){

        Waybill waybill = dataManager.create(Waybill.class);

        WaybillItem item1 = dataManager.create(WaybillItem.class);

        // Заполняем weight
        item1.setWeight(1.0);
        item1.setWaybill(waybill);

        // Заполняем charge
        Dimensions dim1 = dataManager.create(Dimensions.class);
        dim1.setHeight(1.0);
        dim1.setLength(1.0);
        dim1.setWight(1.0);

        item1.setDim(dim1);
        waybillItemService.calcChargeField(item1);

        dataManager.save(waybill);
        dataManager.save(item1);

        WaybillItem item2 = dataManager.create(WaybillItem.class);

        // Заполняем weight
        item2.setWeight(1.0);
        item2.setWaybill(waybill);

        // Заполняем charge
        Dimensions dim2 = dataManager.create(Dimensions.class);

        dim2.setHeight(2.0);
        dim2.setLength(2.0);
        dim2.setWight(2.0);

        item2.setDim(dim2);
        waybillItemService.calcChargeField(item2);

        dataManager.save(item2);

        entitiesToRemove.addAll(List.of(item1, item2, waybill));

        Waybill saved = dataManager.load(Waybill.class).id(waybill.getId()).one();

        // Проверка поля TotalWeight
        Assertions.assertEquals(2, saved.getTotalWeight());

        // Проверка поля TotalCharge
        Assertions.assertEquals(BigDecimal.valueOf(9), saved.getTotalCharge());

    }
    @Test
    public void checkTotalWeightEdit2Field(){

        Waybill waybill = dataManager.create(Waybill.class);

        WaybillItem item1 = dataManager.create(WaybillItem.class);

        item1.setWeight(1.0);
        item1.setWaybill(waybill);

        // Заполняем charge
        Dimensions dim1 = dataManager.create(Dimensions.class);
        dim1.setHeight(1.0);
        dim1.setLength(1.0);
        dim1.setWight(1.0);

        item1.setDim(dim1);
        waybillItemService.calcChargeField(item1);

        WaybillItem item2 = dataManager.create(WaybillItem.class);

        item2.setWeight(1.0);
        item2.setWaybill(waybill);

        // Заполняем charge
        Dimensions dim2 = dataManager.create(Dimensions.class);

        dim2.setHeight(2.0);
        dim2.setLength(2.0);
        dim2.setWight(2.0);

        item2.setDim(dim2);
        waybillItemService.calcChargeField(item2);

        dataManager.save(waybill);
        dataManager.save(item1, item2);

        item2.setWeight(2.0);

        item2.getDim().setWight(1.0);
        item2.getDim().setHeight(1.0);
        item2.getDim().setLength(1.0);

        waybillItemService.calcChargeField(item2);

        dataManager.save(item2);

        entitiesToRemove.addAll(List.of(item1, item2, waybill));

        Waybill saved = dataManager.load(Waybill.class).id(waybill.getId()).one();

        // Проверка поля TotalWeight
        Assertions.assertEquals(3, saved.getTotalWeight());

        // Проверка поля TotalCharge
        Assertions.assertEquals(BigDecimal.valueOf(2), saved.getTotalCharge());

    }

    @Test
    public void calcNumber(){

        Waybill waybill = dataManager.create(Waybill.class);

        WaybillItem item1 = dataManager.create(WaybillItem.class);

        item1.setNumber(1);
        item1.setWaybill(waybill);

        WaybillItem item2 = dataManager.create(WaybillItem.class);

        item2.setNumber(2);
        item2.setWaybill(waybill);

        WaybillItem item3 = dataManager.create(WaybillItem.class);

        item3.setNumber(3);
        item3.setWaybill(waybill);

        dataManager.save(waybill);
        dataManager.save(item1, item2, item3);

        entitiesToRemove.addAll(List.of(item1, item2, item3, waybill));

        dataManager.remove(item2);

        WaybillItem item1s = dataManager.load(WaybillItem.class).id(item1.getId()).one();
        WaybillItem item3s = dataManager.load(WaybillItem.class).id(item3.getId()).one();

        assertThat(item1s).extracting(WaybillItem::getNumber).isEqualTo(1);
        assertThat(item3s).extracting(WaybillItem::getNumber).isEqualTo(2);

        //Assertions.assertEquals(1, item1s.getNumber());
        //Assertions.assertEquals(2, item3s.getNumber());

    }
}