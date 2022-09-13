package com.company.space.screen.gas;

import io.jmix.ui.screen.*;
import com.company.space.entity.Gas;

@UiController("sp_Gas.edit")
@UiDescriptor("gas-edit.xml")
@EditedEntityContainer("gasDc")
public class GasEdit extends StandardEditor<Gas> {
}