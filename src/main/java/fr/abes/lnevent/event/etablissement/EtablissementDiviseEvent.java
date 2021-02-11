package fr.abes.lnevent.event.etablissement;

import fr.abes.lnevent.event.Event;
import fr.abes.lnevent.dto.etablissement.EtablissementDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class EtablissementDiviseEvent extends Event {
    private String ancienSiren;
    private ArrayList<EtablissementDTO> etablissements;

    public EtablissementDiviseEvent(Object source, String ancienSiren, ArrayList<EtablissementDTO> etablissements) {
        super(source);
        this.ancienSiren = ancienSiren;
        this.etablissements = etablissements;
    }
}
