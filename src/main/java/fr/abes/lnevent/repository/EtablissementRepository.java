package fr.abes.lnevent.repository;

import fr.abes.lnevent.entities.ContactEntity;
import fr.abes.lnevent.entities.EtablissementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EtablissementRepository extends JpaRepository<EtablissementEntity, Long> {
    void deleteBySiren(String siren);
    EtablissementEntity getFirstBySiren(String siren);

  /*  @Query("select e.name from EtablissementEntity e where e.siren like :x")
    String getNomEtabBySiren(@Param("x") String siren);

    @Query("select e.name from EtablissementEntity e where e.contact.id like :x")
    String getNomEtabByIdContact(@Param("x") Long idContact);

    @Query("select e.contact from EtablissementEntity e where e.siren like :x")
    ContactEntity getContactBySiren(@Param("x") String siren);

    @Query("select e.siren from EtablissementEntity e where e.contact.id like :x")
    String getSirenByIdContact(@Param("x") Long idContact);*/

    @Query(nativeQuery = true, value = "select case when exists(select * from Etablissement "
            + "where siren = :siren) then 'true' else 'false' end from dual")
    Boolean existeSiren(@Param("siren") String siren);
}
