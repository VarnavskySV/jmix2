package com.company.space.screen.moon;

import io.jmix.ui.screen.*;
import com.company.space.entity.Moon;

@UiController("sp_Moon.edit")
@UiDescriptor("moon-edit.xml")
@EditedEntityContainer("moonDc")
public class MoonEdit extends StandardEditor<Moon> {
}