package fr.abes.lnevent.repository;

import fr.abes.lnevent.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {
    void deleteBySiren(String siren);
}
