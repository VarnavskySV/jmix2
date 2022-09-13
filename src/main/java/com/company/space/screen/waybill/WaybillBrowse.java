package com.company.space.screen.waybill;

import io.jmix.ui.screen.*;
import com.company.space.entity.Waybill;

@UiController("sp_Waybill.browse")
@UiDescriptor("waybill-browse.xml")
@LookupComponent("waybillsTable")
public class WaybillBrowse extends StandardLookup<Waybill> {
}