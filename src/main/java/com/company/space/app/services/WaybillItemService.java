package com.company.space.app.services;

import com.company.space.entity.CustomerGrade;
import com.company.space.entity.Discounts;
import com.company.space.entity.Waybill;
import com.company.space.entity.WaybillItem;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Component
public class WaybillItemService {

    @Autowired
    DataManager dataManager;

    public void calcChargeField(WaybillItem waybillItem){

        BigDecimal charge;

        Double height = waybillItem.getDim().getHeight() == null ? 1: waybillItem.getDim().getHeight();

        Double wight = waybillItem.getDim().getWight() == null ? 1: waybillItem.getDim().getWight();

        Double length = waybillItem.getDim().getLength() == null ? 1: waybillItem.getDim().getLength();

        charge = BigDecimal.valueOf(height * wight * length);

        waybillItem.setCharge(charge);


    }

    public WaybillItem getNextNumber(WaybillItem waybillItem){

        Integer num = 0;

        List<WaybillItem> sorted = waybillItem.getWaybill().getItems().stream()
                .sorted(Comparator.comparing(WaybillItem::getNumber))
                .collect(Collectors.toList());

        if (sorted.size() > 0){
            num = sorted.get(sorted.size()-1).getNumber();
        }

        num++;

        waybillItem.setNumber(num);

        return waybillItem;

    }
    public List<WaybillItem> swapNumber(List<WaybillItem> waybillItems, WaybillItem waybillItem, int direction) {

        Integer number = waybillItem.getNumber() + direction;

        List<WaybillItem> swapItems = waybillItems.stream()
                .filter(item->item.getNumber().equals(number))
                .collect(Collectors.toList());

        if (swapItems.size() == 0){

            return new ArrayList<>();

        }
        WaybillItem swapItem = swapItems.get(0);
        swapItem.setNumber(waybillItem.getNumber());
        waybillItem.setNumber(number);


        return List.of(waybillItem, swapItem);

    }
    public void recalcNumber(List<WaybillItem> waybillItems){

        List<WaybillItem> sorted = waybillItems.stream()
                .sorted(Comparator.comparing(WaybillItem::getNumber))
                .collect(Collectors.toList());

        AtomicReference<Integer> number = new AtomicReference<>(1);
        sorted.forEach(item->{
            item.setNumber(number.get());
            number.getAndSet(number.get() + 1);
        });

    }

    public void calculateTotalWeightFields(Waybill waybill, List<WaybillItem> waybillItems, int direction) {


        Double weigth = waybillItems.stream()
                .mapToDouble(item -> item.getWeight() == null ? 0 : item.getWeight()).sum();

        waybill.setTotalWeight(weigth);

    }

    public void calculateTotalChargeFields(Waybill waybill, List<WaybillItem> waybillItems) {


        BigDecimal charge = waybillItems.stream()
                .map(WaybillItem::getCharge)
                .filter(Objects::nonNull)
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

    }

}
