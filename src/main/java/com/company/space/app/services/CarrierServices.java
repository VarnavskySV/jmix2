package com.company.space.app.services;

import com.company.space.entity.Carrier;
import io.jmix.core.repository.JmixDataRepository;
import io.jmix.core.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.UUID;

public interface CarrierServices extends JmixDataRepository<Carrier, UUID>{


    /*
    @Query("select e.* from sp_Carrier e " +
            "where e.id in (select l.carrier_id from sp_carrier_spaceport_link l where l.spaceport_id in ?1"
            , nativeQuery = true)
*/
    @Query("select e from sp_Carrier e " +
            "where e.id in (select c.id from sp_Carrier c inner join c.ports p where p.id in ?1)")
    List<Carrier> getCarriersByPorts(List<UUID> ports);


}
