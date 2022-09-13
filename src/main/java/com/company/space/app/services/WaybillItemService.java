package com.company.space.app.services;

import com.company.space.entity.WaybillItem;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WaybillItemService {

    @Autowired
    DataManager dataManager;

    public void calcChargeField(WaybillItem waybillItem){

        BigDecimal charge = BigDecimal.ZERO;

        Double height = waybillItem.getDim().getHeight() == null ? 1: waybillItem.getDim().getHeight();

        Double wight = waybillItem.getDim().getWight() == null ? 1: waybillItem.getDim().getWight();

        Double length = waybillItem.getDim().getLength() == null ? 1: waybillItem.getDim().getLength();

        charge = BigDecimal.valueOf(height * wight * length);

        waybillItem.setCharge(charge);

        return;

    }

    public WaybillItem getNextNumber(WaybillItem waybillItem){

        Integer num = 0;

        List<WaybillItem> sorted = waybillItem.getWaybill().getItems().stream()
                .sorted((o1, o2)->o1.getNumber().compareTo(o2.getNumber()))
                .collect(Collectors.toList());

        if (sorted.size() > 0){
            num = sorted.get(sorted.size()-1).getNumber();
        }

        num++;

        waybillItem.setNumber(num);

        return waybillItem;

    }
}
