package fr.abes.lnevent.dto.etablissement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EtablissementDTO {

    private String nom;

    private String adresse;

    private String siren;

    private String typeEtablissement;

    private String idAbes;

    private String motDePasse;

    private String nomContact;

    private String prenomContact;

    private String telephoneContact;

    private String mailContact;

    private String adresseContact;

}
