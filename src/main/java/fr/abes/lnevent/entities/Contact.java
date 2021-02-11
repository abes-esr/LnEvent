package fr.abes.lnevent.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Entity
@Table(name = "CONTACT_LN_EVENT")
@Getter
@NoArgsConstructor
public class Contact {

    public Contact(String id,
                      String nom,
                      String prenom,
                      String mail,
                      String motDePasse,
                      String telephone,
                      String adresse,
                      String siren) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.telephone = telephone;
        this.adresse = adresse;
        this.siren = siren;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String id;

    public String nom;

    public String prenom;

    public String mail;

    public String motDePasse;

    public String telephone;

    public String adresse;

    public String siren;

}
