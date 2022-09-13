package com.company.space.screen.individual;

import io.jmix.ui.screen.*;
import com.company.space.entity.Individual;

@UiController("sp_Individual.edit")
@UiDescriptor("individual-edit.xml")
@EditedEntityContainer("individualDc")
public class IndividualEdit extends StandardEditor<Individual> {
}