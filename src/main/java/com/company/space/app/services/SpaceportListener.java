package com.company.space.app.services;

import com.company.space.entity.Spaceport;
import io.jmix.core.DataManager;
import io.jmix.core.event.EntityChangedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SpaceportListener {

    @Autowired
    DataManager dataManager;

    @EventListener
    public void onSpaceportChangedEvent(EntityChangedEvent<Spaceport> event) {
        if (event.getType() == EntityChangedEvent.Type.DELETED){
            return;
        }

        Spaceport spaceport = dataManager.load(event.getEntityId()).one();
        if (spaceport.getIsDefault()){
            dataManager.load(Spaceport.class)
                    .query("select s from sp_Spaceport s " +
                            "where s.isDefault= :isDefault " +
                            "and ( (:planet is not null and s.planet = :planet) " +
                            "or (:moon is not null and s.moon = :moon)) ")
                    .parameter("isDefault", true)
                    .parameter("planet", spaceport.getPlanet())
                    .parameter("moon", spaceport.getMoon())
                    .list().stream()
                    .filter(entity-> !spaceport.getId().equals(entity.getId()))
                    .forEach(entity->{entity.setIsDefault(false); dataManager.save(entity);
                });
        }
    }

}
