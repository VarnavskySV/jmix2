package com.company.space.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "SP_WAYBILL", indexes = {
        @Index(name = "IDX_SP_WAYBILL_CREATOR", columnList = "CREATOR_ID"),
        @Index(name = "IDX_SP_WAYBILL_CUSTOMER", columnList = "SHIPPER_ID"),
        @Index(name = "IDX_SP_WAYBILL_CONSIGNEE", columnList = "CONSIGNEE_ID"),
        @Index(name = "IDX_SP_WAYBILL_DEPARTURE_PORT", columnList = "DEPARTURE_PORT_ID"),
        @Index(name = "IDX_SPWAYBILL_DESTINATIONPORT", columnList = "DESTINATION_PORT_ID"),
        @Index(name = "IDX_SP_WAYBILL_CARRIER", columnList = "CARRIER_ID")
})
@Entity(name = "sp_Waybill")
public class Waybill {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "REFERENCE", length = 150)
    private String reference;

    @JoinColumn(name = "CREATOR_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private User creator;

    @JoinColumn(name = "SHIPPER_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Customer shipper;

    @JoinColumn(name = "CONSIGNEE_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Customer consignee;

    @JoinColumn(name = "DEPARTURE_PORT_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Spaceport departurePort;

    @JoinColumn(name = "DESTINATION_PORT_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Spaceport destinationPort;

    @JoinColumn(name = "CARRIER_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Carrier carrier;

    @Composition
    @OneToMany(mappedBy = "waybill")
    private List<WaybillItem> items;

    @Column(name = "TOTAL_WEIGHT")
    @DependsOnProperties({"items"})
    private Double totalWeight;

    @Column(name = "TOTAL_CHARGE")
    @DependsOnProperties({"items"})
    private BigDecimal totalCharge;

    public List<WaybillItem> getItems() {
        return items;
    }

    public void setItems(List<WaybillItem> items) {
        this.items = items;
    }

    public BigDecimal getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(BigDecimal totalCharge) {
        this.totalCharge = totalCharge;
    }

    public Double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(Double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    public Spaceport getDestinationPort() {
        return destinationPort;
    }

    public void setDestinationPort(Spaceport destinationPort) {
        this.destinationPort = destinationPort;
    }

    public Spaceport getDeparturePort() {
        return departurePort;
    }

    public void setDeparturePort(Spaceport departurePort) {
        this.departurePort = departurePort;
    }

    public Customer getConsignee() {
        return consignee;
    }

    public void setConsignee(Customer consignee) {
        this.consignee = consignee;
    }

    public Customer getShipper() {
        return shipper;
    }

    public void setShipper(Customer shipper) {
        this.shipper = shipper;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}