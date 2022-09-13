package com.company.space.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@JmixEntity(name = "sp_Dimensions")
@Embeddable
public class Dimensions {

    @Column(name = "LENGTH")
    private Double length;

    @Column(name = "WIGHT")
    private Double wight;

    @Column(name = "HEIGHT")
    private Double height;

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWight() {
        return wight;
    }

    public void setWight(Double wight) {
        this.wight = wight;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }
}