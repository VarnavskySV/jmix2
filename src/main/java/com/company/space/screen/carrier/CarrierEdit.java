package com.company.space.screen.carrier;

import io.jmix.ui.screen.*;
import com.company.space.entity.Carrier;

@UiController("sp_Carrier.edit")
@UiDescriptor("carrier-edit.xml")
@EditedEntityContainer("carrierDc")
public class CarrierEdit extends StandardEditor<Carrier> {
}