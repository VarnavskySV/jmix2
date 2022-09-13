package com.company.space.entity;

import io.jmix.core.metamodel.annotation.Composition;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@JmixEntity
@Table(name = "SP_MOON", indexes = {
        @Index(name = "IDX_SP_MOON_ATMOSPHERE", columnList = "ATMOSPHERE_ID"),
        @Index(name = "IDX_SP_MOON_UNQ", columnList = "NAME", unique = true)
})
@Entity(name = "sp_Moon")
public class Moon extends AstronomicalBody {

    @JoinColumn(name = "ATMOSPHERE_ID")
    @Composition
    @OneToOne(fetch = FetchType.LAZY)
    private Atmosphere atmosphere;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "moon")
    private Spaceport spaceport;

    public Spaceport getSpaceport() {
        return spaceport;
    }

    public void setSpaceport(Spaceport spaceport) {
        this.spaceport = spaceport;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

}