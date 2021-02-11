package fr.abes.lnevent.listener.editeur;

import fr.abes.lnevent.event.editeur.EditeurCreeEvent;
import fr.abes.lnevent.repository.EditeurRepository;
import fr.abes.lnevent.entities.Editeur;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EditeurCreeListener implements ApplicationListener<EditeurCreeEvent> {

    private final EditeurRepository editeurRepository;

    public EditeurCreeListener(EditeurRepository editeurRepository) {
        this.editeurRepository = editeurRepository;
    }

    @Override
    public void onApplicationEvent(EditeurCreeEvent editeurCreeEvent) {

        Editeur editeurRow = new Editeur(null,
                editeurCreeEvent.getNom(),
                editeurCreeEvent.getAdresse(),
                editeurCreeEvent.getMailPourBatch(),
                editeurCreeEvent.getMailPourInformation());
        editeurRepository.save(editeurRow);

    }
}
