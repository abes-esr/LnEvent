package fr.abes.lnevent.repository;

import fr.abes.lnevent.entities.Etablissement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtablissementRepository extends JpaRepository<Etablissement, String> {
    void deleteBySiren(String siren);
}
