package com.company.space.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "SP_COMPANY")
@JmixEntity
@Entity(name = "sp_Company")
public class Company extends Customer {

    @Column(name = "REGISTRATION_ID", length = 100)
    private String registrationId;

    @Column(name = "COMPANY_TYPE", length = 100)
    private String companyType;

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

}