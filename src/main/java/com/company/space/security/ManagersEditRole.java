package com.company.space.security;

import com.company.space.entity.AstronomicalBody;
import com.company.space.entity.Atmosphere;
import com.company.space.entity.AtmosphericGas;
import com.company.space.entity.Carrier;
import com.company.space.entity.Company;
import com.company.space.entity.Coordinates;
import com.company.space.entity.Customer;
import com.company.space.entity.Dimensions;
import com.company.space.entity.Discounts;
import com.company.space.entity.Gas;
import com.company.space.entity.Individual;
import com.company.space.entity.Moon;
import com.company.space.entity.Planet;
import com.company.space.entity.Spaceport;
import com.company.space.entity.Waybill;
import com.company.space.entity.WaybillItem;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityui.role.annotation.MenuPolicy;
import io.jmix.securityui.role.annotation.ScreenPolicy;

@ResourceRole(name = "ManagersEditRole", code = "managers-edit-role", scope = "UI")
public interface ManagersEditRole {
    @MenuPolicy(menuIds = {"themeSettingsScreen", "sp_Planet.browse", "sp_Moon.browse", "sp_Spaceport.browse", "sp_Company.browse", "sp_Individual.browse", "sp_Waybill.browse", "sp_Carrier.browse", "sp_Discounts.browse", "sp_Atmosphere.browse", "sp_Gas.browse", "sp_AtmosphericGas.browse"})
    @ScreenPolicy(screenIds = {"themeSettingsScreen", "sp_Planet.browse", "sp_Moon.browse", "sp_Spaceport.browse", "sp_Company.browse", "sp_Individual.browse", "sp_Waybill.browse", "sp_Carrier.browse", "sp_Discounts.browse", "sp_Carrier.edit", "sp_Company.edit", "sp_Discounts.edit", "sp_Individual.edit", "sp_Moon.edit", "sp_Planet.edit", "sp_Spaceport.edit", "sp_Waybill.edit", "sp_WaybillItem.browse", "sp_WaybillItem.edit", "sp_Atmosphere.browse", "sp_Gas.browse", "sp_AtmosphericGas.browse", "sp_Atmosphere.edit", "sp_AtmosphericGas.edit", "sp_Gas.edit"})
    void screens();

    @EntityAttributePolicy(entityClass = AstronomicalBody.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = AstronomicalBody.class, actions = EntityPolicyAction.ALL)
    void astronomicalBody();

    @EntityAttributePolicy(entityClass = Atmosphere.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Atmosphere.class, actions = EntityPolicyAction.ALL)
    void atmosphere();

    @EntityAttributePolicy(entityClass = AtmosphericGas.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = AtmosphericGas.class, actions = EntityPolicyAction.ALL)
    void atmosphericGas();

    @EntityAttributePolicy(entityClass = Carrier.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Carrier.class, actions = EntityPolicyAction.ALL)
    void carrier();

    @EntityAttributePolicy(entityClass = Company.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Company.class, actions = EntityPolicyAction.ALL)
    void company();

    @EntityAttributePolicy(entityClass = Coordinates.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Coordinates.class, actions = EntityPolicyAction.ALL)
    void coordinates();

    @EntityAttributePolicy(entityClass = Customer.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Customer.class, actions = EntityPolicyAction.ALL)
    void customer();

    @EntityAttributePolicy(entityClass = Dimensions.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Dimensions.class, actions = EntityPolicyAction.ALL)
    void dimensions();

    @EntityAttributePolicy(entityClass = Discounts.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Discounts.class, actions = EntityPolicyAction.ALL)
    void discounts();

    @EntityAttributePolicy(entityClass = Gas.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Gas.class, actions = EntityPolicyAction.ALL)
    void gas();

    @EntityAttributePolicy(entityClass = Individual.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Individual.class, actions = EntityPolicyAction.ALL)
    void individual();

    @EntityAttributePolicy(entityClass = Moon.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Moon.class, actions = EntityPolicyAction.ALL)
    void moon();

    @EntityAttributePolicy(entityClass = Planet.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Planet.class, actions = EntityPolicyAction.ALL)
    void planet();

    @EntityAttributePolicy(entityClass = Spaceport.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Spaceport.class, actions = EntityPolicyAction.ALL)
    void spaceport();

    @EntityAttributePolicy(entityClass = Waybill.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = Waybill.class, actions = EntityPolicyAction.ALL)
    void waybill();

    @EntityAttributePolicy(entityClass = WaybillItem.class, attributes = "*", action = EntityAttributePolicyAction.MODIFY)
    @EntityPolicy(entityClass = WaybillItem.class, actions = EntityPolicyAction.ALL)
    void waybillItem();
}