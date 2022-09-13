package com.company.space.entity;

import io.jmix.core.entity.annotation.EmbeddedParameters;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

@JmixEntity
@Table(name = "SP_WAYBILL_ITEM", indexes = {
        @Index(name = "IDX_SP_WAYBILL_ITEM_WAYBILL", columnList = "WAYBILL_ID")
})
@Entity(name = "sp_WaybillItem")
public class WaybillItem {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "NUMBER_")
    private Integer number;

    @InstanceName
    @Column(name = "NAME", length = 150)
    private String name;

    @Column(name = "WEIGHT")
    private Double weight;

    @EmbeddedParameters(nullAllowed = false)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "length", column = @Column(name = "DIM_LENGTH")),
            @AttributeOverride(name = "wight", column = @Column(name = "DIM_WIGHT")),
            @AttributeOverride(name = "height", column = @Column(name = "DIM_HEIGHT"))
    })
    private Dimensions dim;

    @Column(name = "CHARGE", precision = 19, scale = 2)
    private BigDecimal charge;

    @JoinColumn(name = "WAYBILL_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Waybill waybill;

    public Waybill getWaybill() {
        return waybill;
    }

    public void setWaybill(Waybill waybill) {
        this.waybill = waybill;
    }

    public Dimensions getDim() {
        return dim;
    }

    public void setDim(Dimensions dim) {
        this.dim = dim;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}