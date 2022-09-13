package com.company.space.app.services;

import com.company.space.entity.Spaceport;
import io.jmix.ui.Notifications;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpaceportService {


    public void checkSpaceport(Spaceport spaceport) throws Exception{

        if (spaceport.getMoon() != null && spaceport.getPlanet() != null){
            throw new Exception("Планета и спутник не могут быть заполнены одновременно");
        }

        return;

    }
}
