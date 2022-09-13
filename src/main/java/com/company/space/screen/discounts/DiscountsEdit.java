package com.company.space.screen.discounts;

import io.jmix.ui.screen.*;
import com.company.space.entity.Discounts;

@UiController("sp_Discounts.edit")
@UiDescriptor("discounts-edit.xml")
@EditedEntityContainer("discountsDc")
public class DiscountsEdit extends StandardEditor<Discounts> {
}