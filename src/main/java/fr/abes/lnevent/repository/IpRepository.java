package fr.abes.lnevent.repository;

import fr.abes.lnevent.entities.Ip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IpRepository extends JpaRepository<Ip, String> {

    List<Ip> findAllBySiren(String siren);
    void deleteByIpAndSiren(String ip, String siren);
}
