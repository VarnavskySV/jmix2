package com.company.space.screen.spaceport;

import com.company.space.app.services.SpaceportService;
import com.company.space.entity.Planet;
import com.company.space.entity.Spaceport;
import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.component.ValidationErrors;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.screen.EditedEntityContainer;
import io.jmix.ui.screen.StandardEditor;
import io.jmix.ui.screen.Subscribe;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
import org.springframework.beans.factory.annotation.Autowired;

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