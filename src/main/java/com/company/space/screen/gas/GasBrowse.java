package com.company.space.screen.gas;

import io.jmix.ui.screen.*;
import com.company.space.entity.Gas;

@UiController("sp_Gas.browse")
@UiDescriptor("gas-browse.xml")
@LookupComponent("gasesTable")
public class GasBrowse extends StandardLookup<Gas> {
}