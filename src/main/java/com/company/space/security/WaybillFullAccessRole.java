package com.company.space.security;

import com.company.space.entity.Waybill;
import io.jmix.security.role.annotation.JpqlRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;

@RowLevelRole(name="Waybill full access", code = WaybillFullAccessRole.CODE)
public interface WaybillFullAccessRole {
    String CODE = "waybill-full-access";

    @JpqlRowLevelPolicy(entityClass = Waybill.class, where = "")
    void noRestrictWaybill();

}