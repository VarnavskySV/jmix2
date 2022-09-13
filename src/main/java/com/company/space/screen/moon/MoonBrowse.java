package com.company.space.screen.moon;

import io.jmix.ui.screen.*;
import com.company.space.entity.Moon;

@UiController("sp_Moon.browse")
@UiDescriptor("moon-browse.xml")
@LookupComponent("moonsTable")
public class MoonBrowse extends StandardLookup<Moon> {
}