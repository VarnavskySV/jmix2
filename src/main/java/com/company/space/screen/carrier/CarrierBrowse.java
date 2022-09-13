package com.company.space.screen.carrier;

import com.company.space.app.services.CarrierServices;
import io.jmix.core.DataManager;
import io.jmix.core.LoadContext;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import com.company.space.entity.Carrier;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@UiController("sp_Carrier.browse")
@UiDescriptor("carrier-browse.xml")
@LookupComponent("carriersTable")
public class CarrierBrowse extends StandardLookup<Carrier> {

    //private List<UUID> ports = new ArrayList<>();

    @Autowired
    private DataManager dataManager;

    @Autowired
    private CollectionLoader<Carrier> carriersDl;

    @Autowired
    private CarrierServices carrierServices;

    public void setPorts(List<UUID> ports) {
        //this.ports = ports;
        carriersDl.setParameter("ports", ports);
    }

/*
    @Install(to = "carriersDl", target = Target.DATA_LOADER)
    private List<Carrier> carriersDlLoadDelegate(LoadContext<Carrier> loadContext) {

        if (ports.size() > 0){
            return carrierServices.getCarriersByPorts(ports);
        }

        return  dataManager.loadList(loadContext);

    }
*/


}