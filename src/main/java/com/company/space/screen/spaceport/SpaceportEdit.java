package com.company.space.screen.spaceport;

import com.company.space.app.services.SpaceportService;
import com.company.space.entity.Carrier;
import com.company.space.entity.Planet;
import io.jmix.core.SaveContext;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.component.ValidationErrors;
import io.jmix.ui.model.CollectionPropertyContainer;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.*;
import com.company.space.entity.Spaceport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

@UiController("sp_Spaceport.edit")
@UiDescriptor("spaceport-edit.xml")
@EditedEntityContainer("spaceportDc")
public class SpaceportEdit extends StandardEditor<Spaceport> {

    @Autowired
    private InstanceContainer<Spaceport> spaceportDc;

    @Autowired
    private SpaceportService spaceportService;

    @Autowired
    private EntityPicker<Planet> planetField;


    @Subscribe
    public void onValidation(ValidationEvent event) {
        try {
            spaceportService.checkSpaceport(spaceportDc.getItem());
        }catch (Exception e){
            ValidationErrors errors = new ValidationErrors();
            errors.add(planetField, e.getMessage());
            event.addErrors(errors);
        }
    }

}