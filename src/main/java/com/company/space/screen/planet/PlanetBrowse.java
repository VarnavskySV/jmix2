package com.company.space.screen.planet;

import com.company.space.entity.Atmosphere;
import io.jmix.core.DataManager;
import io.jmix.ui.component.FileStorageUploadField;
import io.jmix.ui.component.SingleFileUploadField;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import com.company.space.entity.Planet;
import io.jmix.ui.upload.TemporaryStorage;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@UiController("sp_Planet.browse")
@UiDescriptor("planet-browse.xml")
@LookupComponent("planetsTable")
public class PlanetBrowse extends StandardLookup<Planet> {

    @Autowired
    private TemporaryStorage temporaryStorage;

    @Autowired
    private FileStorageUploadField loadPlanet;

    @Autowired
    private DataManager dataManager;

    @Autowired
    private CollectionLoader<Planet> planetsDl;

    @Subscribe("loadPlanet")
    public void onLoadPlanetFileUploadSucceed(SingleFileUploadField.FileUploadSucceedEvent event) throws IOException {
        UUID fileId = loadPlanet.getFileId();

        File file = temporaryStorage.getFile(fileId);

        List<String> planets = FileUtils.readLines(file, StandardCharsets.UTF_8);

        file.delete();

        String planetName = "";
        Double planetMass = 0.0;
        Double planetSemi = 0.0;
        Double planetOrbital = 0.0;
        Double planetRotation = 0.0;
        Boolean rings = false;
        String atmosphere = "";
        int i = 0;
        List<Object> entities = new ArrayList<>();
        for (String line : planets){
            i++;
            if (i==1){
                continue;
            }

            String[] lines = line.split(",");

            List<String> stringList = new ArrayList<String>(Arrays.asList(lines));
            planetName = stringList.get(0);

            String str = "?".equals(stringList.get(2)) ? "0" : stringList.get(2);
            planetMass = Double.valueOf(str);

            str = "?".equals(stringList.get(3)) ? "0" : stringList.get(3);
            planetSemi = Double.valueOf(str);

            str = "?".equals(stringList.get(4)) ? "0" : stringList.get(4);
            planetOrbital = Double.valueOf(str);

            str = "?".equals(stringList.get(7)) ? "0" : stringList.get(7);
            planetRotation = Double.valueOf(str);

            rings = "yes".equals(stringList.get(10)) ? true : false;

            atmosphere = "?".equals(stringList.get(11)) ? "" : stringList.get(11);
            str = "%s - mass: %f semi: %f orbital:%f rotation:%f";

            str = String.format(str, planetName, planetMass, planetSemi, planetOrbital, planetRotation);

            System.out.println(str);

            // Создадим планеты, проверив на наличие по имени.
            Planet planet = dataManager.load(Planet.class)
                    .query("select p from sp_Planet p where p.name = :name")
                    .parameter("name", planetName)
                    .optional().orElse(null);

            if (planet == null){
                planet = dataManager.create(Planet.class);
                planet.setName(planetName);
            }

            planet.setMass(planetMass);
            planet.setSemiMajorAxis(planetSemi);
            planet.setOrbitalPeriod(planetOrbital);
            planet.setRotationPeriod(planetRotation);
            planet.setRings(rings);

            entities.add(planet);

            // Создадим объект атмосфера по дескрипшиону
            if (planet.getAtmosphere() == null && !atmosphere.equals("")){
                Atmosphere atmosphere1 = dataManager.load(Atmosphere.class)
                        .query("select a from sp_Atmosphere a where a.description = :description")
                        .parameter("description", atmosphere)
                        .optional().orElse(null);

                if (atmosphere1 == null){
                    atmosphere1 = dataManager.create(Atmosphere.class);
                    atmosphere1.setDescription(atmosphere);

                    entities.add(atmosphere1);
                    dataManager.save(atmosphere1);

                }

                planet.setAtmosphere(atmosphere1);

            }
            dataManager.save(planet);

        }

        planetsDl.load();

    }
}