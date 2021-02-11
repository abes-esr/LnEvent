package fr.abes.lnevent.repository;

import fr.abes.lnevent.entities.Editeur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditeurRepository extends JpaRepository<Editeur, String> {
    void deleteById(String id);
}
