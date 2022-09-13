package com.company.space.screen.company;

import io.jmix.ui.screen.*;
import com.company.space.entity.Company;

@UiController("sp_Company.browse")
@UiDescriptor("company-browse.xml")
@LookupComponent("companiesTable")
public class CompanyBrowse extends StandardLookup<Company> {
}