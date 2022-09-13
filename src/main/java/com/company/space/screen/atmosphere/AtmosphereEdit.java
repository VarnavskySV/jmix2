package com.company.space.screen.atmosphere;

import io.jmix.ui.screen.*;
import com.company.space.entity.Atmosphere;

@UiController("sp_Atmosphere.edit")
@UiDescriptor("atmosphere-edit.xml")
@EditedEntityContainer("atmosphereDc")
public class AtmosphereEdit extends StandardEditor<Atmosphere> {
}