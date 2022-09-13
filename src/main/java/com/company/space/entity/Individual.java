package com.company.space.entity;

import io.jmix.core.metamodel.annotation.DependsOnProperties;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table(name = "SP_INDIVIDUAL")
@JmixEntity
@Entity(name = "sp_Individual")
public class Individual extends Customer {

    @InstanceName
    @Column(name = "FIRST_NAME", nullable = false, length = 100)
    @NotNull
    private String firstName;

    @Column(name = "LAST_NAME", length = 100)
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @InstanceName
    @DependsOnProperties({"name"})
    public String getInstanceName() {
        return String.format("%s", getName());
    }
}