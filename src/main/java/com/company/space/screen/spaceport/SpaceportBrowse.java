package com.company.space.screen.spaceport;

import io.jmix.ui.action.Action;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import com.company.space.entity.Spaceport;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("sp_Spaceport.browse")
@UiDescriptor("spaceport-browse.xml")
@LookupComponent("spaceportsTable")
public class SpaceportBrowse extends StandardLookup<Spaceport> {

    @Autowired
    private CollectionLoader<Spaceport> spaceportsDl;

    private void spaceportDlRefresh(Spaceport spaceport){
        if (spaceport.getIsDefault()){
            spaceportsDl.load();
        }
    }
    @Install(to = "spaceportsTable.edit", subject = "afterCommitHandler")
    private void spaceportsTableEditAfterCommitHandler(Spaceport spaceport) {
        spaceportDlRefresh(spaceport);
    }

    @Install(to = "spaceportsTable.create", subject = "afterCommitHandler")
    private void spaceportsTableCreateAfterCommitHandler(Spaceport spaceport) {
        spaceportDlRefresh(spaceport);
    }

}