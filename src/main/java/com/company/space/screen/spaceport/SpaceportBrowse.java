package com.company.space.screen.spaceport;

import com.company.space.entity.Spaceport;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.Install;
import io.jmix.ui.screen.LookupComponent;
import io.jmix.ui.screen.StandardLookup;
import io.jmix.ui.screen.UiController;
import io.jmix.ui.screen.UiDescriptor;
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