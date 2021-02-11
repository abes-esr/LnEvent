package fr.abes.lnevent.entities;

import fr.abes.lnevent.dto.etablissement.EtablissementDTO;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

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
        if (etablissementDTO.getTypeEtablissement() != null && !etablissementDTO.getTypeEtablissement()
                .isEmpty()) {
            sb.append(etablissementDTO.getTypeEtablissement());
            sb.append(SEPARATOR);
        }

        if (etablissementDTO.getAdresse() != null
                && !etablissementDTO.getAdresse().isEmpty()) {
            sb.append(etablissementDTO.getAdresse());
        }

        return sb.toString();
    }

    @Override
    public EtablissementDTO convertToEntityAttribute(String s) {
        return null;
    }
}
