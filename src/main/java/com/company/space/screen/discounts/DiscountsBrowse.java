package com.company.space.screen.discounts;

import io.jmix.ui.screen.*;
import com.company.space.entity.Discounts;

@UiController("sp_Discounts.browse")
@UiDescriptor("discounts-browse.xml")
@LookupComponent("discountsesTable")
public class DiscountsBrowse extends StandardLookup<Discounts> {
}