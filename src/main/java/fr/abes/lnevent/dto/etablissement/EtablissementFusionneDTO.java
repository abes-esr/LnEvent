package fr.abes.lnevent.dto.etablissement;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class EtablissementFusionneDTO {
    private EtablissementDTO etablissement;
    private ArrayList<String> sirenFusionnes;
}
