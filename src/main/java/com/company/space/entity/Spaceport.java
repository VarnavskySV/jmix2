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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@JmixEntity
@Table(name = "SP_SPACEPORT", indexes = {
        @Index(name = "IDX_SP_SPACEPORT_PLANET", columnList = "PLANET_ID"),
        @Index(name = "IDX_SP_SPACEPORT_MOON", columnList = "MOON_ID"),
        @Index(name = "IDX_SP_SPACEPORT_UNQ", columnList = "NAME", unique = true)
})
@Entity(name = "sp_Spaceport")
public class Spaceport {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @InstanceName
    @Column(name = "NAME", nullable = false, length = 100)
    @NotNull
    private String name;

    @JoinColumn(name = "PLANET_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Planet planet;

    @JoinColumn(name = "MOON_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private Moon moon;

    @EmbeddedParameters(nullAllowed = false)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "lattitude", column = @Column(name = "COORDINATES_LATTITUDE")),
            @AttributeOverride(name = "longitude", column = @Column(name = "COORDINATES_LONGITUDE"))
    })
    private Coordinates coordinates;

    @Column(name = "IS_DEFAULT")
    private Boolean isDefault;


    @JoinTable(name = "SP_CARRIER_SPACEPORT_LINK",
            joinColumns = @JoinColumn(name = "SPACEPORT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "CARRIER_ID", referencedColumnName = "ID"))
    @ManyToMany
    private List<Carrier> carriers;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public List<Carrier> getCarriers() {
        return carriers;
    }

    public void setCarriers(List<Carrier> carriers) {
        this.carriers = carriers;
    }


    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public Moon getMoon() {
        return moon;
    }

    public void setMoon(Moon moon) {
        this.moon = moon;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}