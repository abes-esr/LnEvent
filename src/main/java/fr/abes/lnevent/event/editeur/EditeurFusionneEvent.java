package fr.abes.lnevent.event.editeur;

import fr.abes.lnevent.event.Event;
import fr.abes.lnevent.dto.editeur.EditeurDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class EditeurFusionneEvent extends Event {

    public EditeurFusionneEvent(Object source, EditeurDTO editeur, List<String> idEditeurFusionnes) {
        super(source);
        this.editeur = editeur;
        this.idEditeurFusionnes = idEditeurFusionnes;
    }

    private EditeurDTO editeur;
    private List<String> idEditeurFusionnes;
}
