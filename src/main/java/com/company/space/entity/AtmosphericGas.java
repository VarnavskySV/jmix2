package com.company.space.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@JmixEntity
@Table(name = "SP_ATMOSPHERIC_GAS", indexes = {
        @Index(name = "IDX_SP_ATMOSPHERIC_GAS_GAS", columnList = "GAS_ID"),
        @Index(name = "IDX_SPATMOSPHERICGA_ATMOSPHERE", columnList = "ATMOSPHERE_ID")
})
@Entity(name = "sp_AtmosphericGas")
public class AtmosphericGas {

    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @JoinColumn(name = "GAS_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Gas gas;

    @Column(name = "VOLUME")
    private Double volume;

    @JoinColumn(name = "ATMOSPHERE_ID", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Atmosphere atmosphere;

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Gas getGas() {
        return gas;
    }

    public void setGas(Gas gas) {
        this.gas = gas;
    }
}