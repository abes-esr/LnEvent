package fr.abes.lnevent.dto.etablissement;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EtablissementCreeDTO extends EtablissementDTO {
    public EtablissementCreeDTO(String nom, String siren, String typeEtablissement, String idAbes, String nomContact, String prenomContact, String adresseContact, String boitePostaleContact, String codePostalContact, String villeContact, String cedexContact, String telephoneContact, String mailContact, String motDePasse, String roleContact) {
        super(nom, siren, typeEtablissement, idAbes,  nomContact, prenomContact, adresseContact, boitePostaleContact, codePostalContact, villeContact, cedexContact, telephoneContact, mailContact, motDePasse, roleContact);
    }
}
