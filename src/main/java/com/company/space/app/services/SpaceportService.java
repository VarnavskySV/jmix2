package com.company.space.app.services;

import com.company.space.entity.AstronomicalBody;
import com.company.space.entity.Moon;
import com.company.space.entity.Planet;
import com.company.space.entity.Spaceport;
import com.company.space.security.UsersBrowsPlanetRole;
import io.jmix.ui.Notifications;
import io.jmix.core.DataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SpaceportService {

    @Autowired
    DataManager dataManager;

    public void checkSpaceport(Spaceport spaceport) throws Exception{

        if (spaceport.getMoon() != null && spaceport.getPlanet() != null){
            throw new Exception("Планета и спутник не могут быть заполнены одновременно");
        }

        return;

    }
    public List<AstronomicalBody> getAstronomicalBody(){

        List<AstronomicalBody> results = new ArrayList<>();

        List<Planet> planets = dataManager.load(Planet.class).all().list();

        planets.forEach(item-> {

            AstronomicalBody astronomicalBody = new AstronomicalBody();
            astronomicalBody.setId(item.getId());
            astronomicalBody.setName(item.getName());

            results.add(astronomicalBody);

        });

        List<Moon> moons = dataManager.load(Moon.class).all().list();

        moons.forEach(item-> {

            AstronomicalBody astronomicalBody = new AstronomicalBody();
            astronomicalBody.setId(item.getId());
            astronomicalBody.setName(item.getName());

            results.add(astronomicalBody);

        });

        return results;
    }
    public Spaceport getDefaultPort(AstronomicalBody astronomicalBody){
            return dataManager.load(Spaceport.class)
                    .query("select s from sp_Spaceport s where s.isDefault = :is_default " +
                            " and (( s.planet is not null and s.planet.id = :id ) or " +
                            "     (s.moon is not null and s.moon.id = :id))")
                    .parameter("id", astronomicalBody.getId())
                    .parameter("is_default", true)
                    .optional().orElse(null);

    }
}
