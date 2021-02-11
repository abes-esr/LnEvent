package fr.abes.lnevent.listener.etablissement;

import fr.abes.lnevent.dto.etablissement.EtablissementDTO;
import fr.abes.lnevent.repository.ContactRepository;
import fr.abes.lnevent.entities.Contact;
import fr.abes.lnevent.entities.Etablissement;
import fr.abes.lnevent.repository.EtablissementRepository;
import fr.abes.lnevent.event.etablissement.EtablissementFusionneEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EtablissementFusionneListener implements ApplicationListener<EtablissementFusionneEvent> {

    private final EtablissementRepository etablissementRepository;
    private final ContactRepository contactRepository;

    public EtablissementFusionneListener(EtablissementRepository etablissementRepository, ContactRepository contactRepository) {
        this.etablissementRepository = etablissementRepository;
        this.contactRepository = contactRepository;
    }

    @Override
    public void onApplicationEvent(EtablissementFusionneEvent etablissementFusionneEvent) {

        EtablissementDTO etablissementFusione = etablissementFusionneEvent.getEtablissement();

        etablissementRepository.save(new Etablissement(null,
                etablissementFusione.getNom(),
                etablissementFusione.getAdresse(),
                etablissementFusione.getSiren(),
                etablissementFusione.getTypeEtablissement(),
                etablissementFusione.getIdAbes()));

        Contact contact =
                new Contact(null,
                        etablissementFusione.getNomContact(),
                        etablissementFusione.getPrenomContact(),
                        etablissementFusione.getMailContact(),
                        etablissementFusione.getMotDePasse(),
                        etablissementFusione.getTelephoneContact(),
                        etablissementFusione.getAdresseContact(),
                        etablissementFusione.getSiren());

        contactRepository.save(contact);

        for (String siren :
                etablissementFusionneEvent.getSirenFusionne()) {
            etablissementRepository.deleteBySiren(siren);
            contactRepository.deleteBySiren(siren);
        }
    }
}
