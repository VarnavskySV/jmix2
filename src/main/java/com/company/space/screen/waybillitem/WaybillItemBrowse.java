package com.company.space.screen.waybillitem;

import io.jmix.ui.screen.*;
import com.company.space.entity.WaybillItem;

@UiController("sp_WaybillItem.browse")
@UiDescriptor("waybill-item-browse.xml")
@LookupComponent("waybillItemsTable")
public class WaybillItemBrowse extends StandardLookup<WaybillItem> {
}