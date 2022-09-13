package com.company.space.screen.atmosphere;

import io.jmix.ui.screen.*;
import com.company.space.entity.Atmosphere;

@UiController("sp_Atmosphere.browse")
@UiDescriptor("atmosphere-browse.xml")
@LookupComponent("atmospheresTable")
public class AtmosphereBrowse extends StandardLookup<Atmosphere> {
}