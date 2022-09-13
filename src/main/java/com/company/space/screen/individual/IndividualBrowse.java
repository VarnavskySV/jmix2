package com.company.space.screen.individual;

import io.jmix.ui.screen.*;
import com.company.space.entity.Individual;

@UiController("sp_Individual.browse")
@UiDescriptor("individual-browse.xml")
@LookupComponent("individualsTable")
public class IndividualBrowse extends StandardLookup<Individual> {
}