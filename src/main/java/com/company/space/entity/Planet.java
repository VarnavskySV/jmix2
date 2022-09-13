package com.company.space.entity;

import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@JmixEntity
@Table(name = "SP_PLANET", indexes = {
        @Index(name = "IDX_SP_PLANET_ATMOSPHERE", columnList = "ATMOSPHERE_ID")
})
@Entity(name = "sp_Planet")
public class Planet extends AstronomicalBody {

    @Column(name = "SEMI_MAJOR_AXIS")
    private Double semiMajorAxis;

    @Column(name = "ORBITAL_PERIOD")
    private Double orbitalPeriod;

    @Column(name = "ROTATION_PERIOD")
    private Double rotationPeriod;

    @JoinColumn(name = "ATMOSPHERE_ID")
    @Composition
    @OneToOne(fetch = FetchType.LAZY)
    private Atmosphere atmosphere;

    @Column(name = "RINGS")
    private Boolean rings;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "planet")
    private Spaceport spaceport;

    public Spaceport getSpaceport() {
        return spaceport;
    }

    public void setSpaceport(Spaceport spaceport) {
        this.spaceport = spaceport;
    }

    public Boolean getRings() {
        return rings;
    }

    public void setRings(Boolean rings) {
        this.rings = rings;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public Double getRotationPeriod() {
        return rotationPeriod;
    }

    public void setRotationPeriod(Double rotationPeriod) {
        this.rotationPeriod = rotationPeriod;
    }

    public Double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public void setOrbitalPeriod(Double orbitalPeriod) {
        this.orbitalPeriod = orbitalPeriod;
    }

    public Double getSemiMajorAxis() {
        return semiMajorAxis;
    }

    public void setSemiMajorAxis(Double semiMajorAxis) {
        this.semiMajorAxis = semiMajorAxis;
    }

}