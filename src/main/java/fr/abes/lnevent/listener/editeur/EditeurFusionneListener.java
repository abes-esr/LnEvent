package fr.abes.lnevent.listener.editeur;

import fr.abes.lnevent.dto.editeur.EditeurDTO;
import fr.abes.lnevent.event.editeur.EditeurFusionneEvent;
import fr.abes.lnevent.repository.EditeurRepository;
import fr.abes.lnevent.entities.Editeur;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EditeurFusionneListener implements ApplicationListener<EditeurFusionneEvent> {

    private final EditeurRepository editeurRepository;

    public EditeurFusionneListener(EditeurRepository editeurRepository) {
        this.editeurRepository = editeurRepository;
    }

    @Override
    public void onApplicationEvent(EditeurFusionneEvent editeurFusionneEvent) {

        EditeurDTO editeur = editeurFusionneEvent.getEditeur();

        editeurRepository.save(new Editeur(null,
                editeur.getNom(),
                editeur.getAdresse(),
                editeur.getMailPourBatch(),
                editeur.getMailPourInformation()));

        for (String idEditeur :
                editeurFusionneEvent.getIdEditeurFusionnes()) {
            editeurRepository.deleteById(idEditeur);
        }
    }
}
