package fr.abes.lnevent.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "EDITEUR_LN_EVENT")
@Getter
@NoArgsConstructor
public class Editeur {
    public Editeur(String id, String nom, String adresse, List<String> mailsPourBatch, List<String> mailPourInformation) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.mailsPourBatch = mailsPourBatch;
        this.mailPourInformation = mailPourInformation;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String id;

    public String nom;

    public String adresse;

    public List<String> mailsPourBatch;

    public List<String> mailPourInformation;
}
