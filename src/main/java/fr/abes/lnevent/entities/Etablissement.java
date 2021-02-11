package fr.abes.lnevent.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;



@Entity
@Table(name = "ETABLISSEMENT_LN_EVENT")
@Getter
@NoArgsConstructor
public class Etablissement {

    public Etablissement(String id, String name, String adresse, String siren, String typeEtablissement, String idAbes) {
        this.id = id;
        this.name = name;
        this.adresse = adresse;
        this.siren = siren;
        this.typeEtablissement = typeEtablissement;
        this.idAbes = idAbes;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String id;

    public String name;

    private String adresse;

    private String siren;

    private String typeEtablissement;

    private String idAbes;
}
