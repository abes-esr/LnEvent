package fr.abes.lnevent.entities;

import fr.abes.lnevent.dto.etablissement.EtablissementDTO;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import org.apache.commons.lang3.StringUtils;

@Converter
public class EtablissementDtoConverter implements
        AttributeConverter<EtablissementDTO, String> {

    private static final String SEPARATOR = ", ";

    @Override
    public String convertToDatabaseColumn(EtablissementDTO etablissementDTO) {
        if (etablissementDTO == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        if (etablissementDTO.getNom() != null && !etablissementDTO.getNom()
                .isEmpty()) {
            sb.append("nom" + etablissementDTO.getNom());
            sb.append("$");
        }

        if (etablissementDTO.getAdresse() != null
                && !etablissementDTO.getAdresse().isEmpty()) {
            sb.append("adresse" + etablissementDTO.getAdresse());
            sb.append("$");
        }

        if (etablissementDTO.getSiren() != null
                && !etablissementDTO.getSiren().isEmpty()) {
            sb.append("siren" + etablissementDTO.getSiren());
            sb.append("$");
        }

        if (etablissementDTO.getTypeEtablissement() != null
                && !etablissementDTO.getTypeEtablissement().isEmpty()) {
            sb.append("typeEtab" + etablissementDTO.getTypeEtablissement());
            sb.append("$");
        }

        if (etablissementDTO.getIdAbes() != null
                && !etablissementDTO.getIdAbes().isEmpty()) {
            sb.append("idAbes" + etablissementDTO.getIdAbes());
            sb.append("$");
        }


        if (etablissementDTO.getMotDePasse() != null
                && !etablissementDTO.getMotDePasse().isEmpty()) {
            sb.append("pwd" + etablissementDTO.getMotDePasse());
            sb.append("$");
        }

        if (etablissementDTO.getNomContact() != null
                && !etablissementDTO.getNomContact().isEmpty()) {
            sb.append("nomContact" + etablissementDTO.getNomContact());
            sb.append("$");
        }

        if (etablissementDTO.getPrenomContact() != null
                && !etablissementDTO.getPrenomContact().isEmpty()) {
            sb.append("prenomContact" + etablissementDTO.getPrenomContact());
            sb.append("$");
        }

        if (etablissementDTO.getTelephoneContact() != null
                && !etablissementDTO.getTelephoneContact().isEmpty()) {
            sb.append("telContact" + etablissementDTO.getTelephoneContact());
            sb.append("$");
        }

        if (etablissementDTO.getMailContact() != null
                && !etablissementDTO.getMailContact().isEmpty()) {
            sb.append("mailContact" + etablissementDTO.getMailContact());
            sb.append("$");
        }

        if (etablissementDTO.getAdresseContact() != null
                && !etablissementDTO.getAdresseContact().isEmpty()) {
            sb.append("adresseContact" + etablissementDTO.getAdresseContact());
        }
        return sb.toString();
    }


    @Override
    public EtablissementDTO convertToEntityAttribute(String etablissementDtoFromDb) {
        if (etablissementDtoFromDb == null || etablissementDtoFromDb.isEmpty()) {
            return null;
        }

        EtablissementDTO etablissementDTO = new EtablissementDTO();

        String nom = StringUtils.substringBetween(etablissementDtoFromDb, "nom", "$");
        String adr =StringUtils.substringBetween(etablissementDtoFromDb, "adresse", "$");
        String siren =StringUtils.substringBetween(etablissementDtoFromDb, "siren", "$");
        String typeEtab =StringUtils.substringBetween(etablissementDtoFromDb, "typeEtab", "$");
        String idAbes =StringUtils.substringBetween(etablissementDtoFromDb, "idAbes", "$");
        String pwd =StringUtils.substringBetween(etablissementDtoFromDb, "pwd", "$");
        String nomContact =StringUtils.substringBetween(etablissementDtoFromDb, "nomContact", "$");
        String prenomContact =StringUtils.substringBetween(etablissementDtoFromDb, "prenomContact", "$");
        String telContact =StringUtils.substringBetween(etablissementDtoFromDb, "telContact", "$");
        String mailContact =StringUtils.substringBetween(etablissementDtoFromDb, "mailContact", "$");
        String adresseContact =StringUtils.substringBetween(etablissementDtoFromDb, "adresseContact", "$");

        etablissementDTO.setNom(nom);
        etablissementDTO.setAdresse(adr);
        etablissementDTO.setSiren(siren);
        etablissementDTO.setTypeEtablissement(typeEtab);
        etablissementDTO.setIdAbes(idAbes);
        etablissementDTO.setMotDePasse(pwd);
        etablissementDTO.setNomContact(nomContact);
        etablissementDTO.setPrenomContact(prenomContact);
        etablissementDTO.setTelephoneContact(telContact);
        etablissementDTO.setMailContact(mailContact);
        etablissementDTO.setAdresseContact(adresseContact);


        return etablissementDTO;
    }
}
