package com.company.space.security;

import com.company.space.entity.Waybill;
import io.jmix.security.role.annotation.JpqlRowLevelPolicy;
import io.jmix.security.role.annotation.RowLevelRole;

@RowLevelRole(name = "Restriction waybill access", code = WaybillRestrictionRole.CODE)
public interface WaybillRestrictionRole {
    String CODE = "waybill-restrictions";

    @JpqlRowLevelPolicy(entityClass = Waybill.class, where = "{E}.creator.id = :current_user_id")
    void restrictWaybill();

}