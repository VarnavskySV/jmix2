package com.company.space.screen.atmosphericgas;

import io.jmix.ui.screen.*;
import com.company.space.entity.AtmosphericGas;

@UiController("sp_AtmosphericGas.edit")
@UiDescriptor("atmospheric-gas-edit.xml")
@EditedEntityContainer("atmosphericGasDc")
public class AtmosphericGasEdit extends StandardEditor<AtmosphericGas> {
}