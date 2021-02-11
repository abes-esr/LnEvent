package fr.abes.lnevent.event.etablissement;

import fr.abes.lnevent.dto.etablissement.EtablissementDTO;
import fr.abes.lnevent.event.Event;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class EtablissementFusionneEvent extends Event {
    private EtablissementDTO etablissement;
    private ArrayList<String> sirenFusionne;
    
    public EtablissementFusionneEvent(Object source, EtablissementDTO etablissement, ArrayList<String> sirenFusionne) {
        super(source);
        this.etablissement = etablissement;
        this.sirenFusionne = sirenFusionne;
    }
}
