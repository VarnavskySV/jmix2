package com.company.space.security;

import com.company.space.entity.AstronomicalBody;
import com.company.space.entity.Atmosphere;
import com.company.space.entity.AtmosphericGas;
import com.company.space.entity.Gas;
import com.company.space.entity.Moon;
import com.company.space.entity.Planet;
import io.jmix.security.model.EntityAttributePolicyAction;
import io.jmix.security.model.EntityPolicyAction;
import io.jmix.security.role.annotation.EntityAttributePolicy;
import io.jmix.security.role.annotation.EntityPolicy;
import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityui.role.annotation.MenuPolicy;
import io.jmix.securityui.role.annotation.ScreenPolicy;

@ResourceRole(name = "UsersBrowsPlanet", code = "users-brows-planet", scope = "UI")
public interface UsersBrowsPlanetRole {

    @MenuPolicy(menuIds = {"sp_Planet.browse", "sp_Moon.browse", "sp_Atmosphere.browse", "sp_Gas.browse", "sp_AtmosphericGas.browse"})
    @ScreenPolicy(screenIds = {"sp_Planet.browse", "sp_Moon.browse", "sp_Moon.edit", "sp_Planet.edit", "sp_Atmosphere.browse", "sp_Atmosphere.edit", "sp_Gas.browse", "sp_AtmosphericGas.browse", "sp_AtmosphericGas.edit", "sp_Gas.edit"})
    void screens();

    @EntityAttributePolicy(entityClass = AstronomicalBody.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = AstronomicalBody.class, actions = EntityPolicyAction.READ)
    void astronomicalBody();

    @EntityPolicy(entityClass = Atmosphere.class, actions = EntityPolicyAction.READ)
    @EntityAttributePolicy(entityClass = Atmosphere.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    void atmosphere();

    @EntityPolicy(entityClass = AtmosphericGas.class, actions = EntityPolicyAction.READ)
    @EntityAttributePolicy(entityClass = AtmosphericGas.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    void atmosphericGas();

    @EntityAttributePolicy(entityClass = Gas.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Gas.class, actions = EntityPolicyAction.READ)
    void gas();

    @EntityAttributePolicy(entityClass = Moon.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Moon.class, actions = EntityPolicyAction.READ)
    void moon();

    @EntityAttributePolicy(entityClass = Planet.class, attributes = "*", action = EntityAttributePolicyAction.VIEW)
    @EntityPolicy(entityClass = Planet.class, actions = EntityPolicyAction.READ)
    void planet();
}