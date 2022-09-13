package com.company.space.screen.planet;

import io.jmix.ui.screen.*;
import com.company.space.entity.Planet;

@UiController("sp_Planet.edit")
@UiDescriptor("planet-edit.xml")
@EditedEntityContainer("planetDc")
public class PlanetEdit extends StandardEditor<Planet> {
}