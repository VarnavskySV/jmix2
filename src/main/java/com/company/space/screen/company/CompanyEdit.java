package com.company.space.screen.company;

import io.jmix.ui.screen.*;
import com.company.space.entity.Company;

@UiController("sp_Company.edit")
@UiDescriptor("company-edit.xml")
@EditedEntityContainer("companyDc")
public class CompanyEdit extends StandardEditor<Company> {
}