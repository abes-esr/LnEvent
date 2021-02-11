package fr.abes.lnevent.listener.etablissement;

import fr.abes.lnevent.entities.Contact;
import fr.abes.lnevent.entities.Etablissement;
import fr.abes.lnevent.repository.ContactRepository;
import fr.abes.lnevent.repository.EtablissementRepository;
import fr.abes.lnevent.event.etablissement.EtablissementCreeEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EtablissementCreeListener implements ApplicationListener<EtablissementCreeEvent> {

    private final EtablissementRepository etablissementRepository;
    private final ContactRepository contactRepository;

    public EtablissementCreeListener(EtablissementRepository etablissementRepository,
                                     ContactRepository contactRepository) {
        this.etablissementRepository = etablissementRepository;
        this.contactRepository = contactRepository;
    }

    @Override
    public void onApplicationEvent(EtablissementCreeEvent etablissementCreeEvent) {
        Etablissement etablissement =
                new Etablissement(null,
                etablissementCreeEvent.getNom(),
                etablissementCreeEvent.getAdresse(),
                etablissementCreeEvent.getSiren(),
                etablissementCreeEvent.getTypeEtablissement(),
                etablissementCreeEvent.getIdAbes());

        etablissementRepository.save(etablissement);

        Contact contact =
                new Contact(null,
                        etablissementCreeEvent.getNomContact(),
                        etablissementCreeEvent.getPrenomContact(),
                        etablissementCreeEvent.getMailContact(),
                        etablissementCreeEvent.getMotDePasse(),
                        etablissementCreeEvent.getTelephoneContact(),
                        etablissementCreeEvent.getAdresseContact(),
                        etablissementCreeEvent.getSiren());

        contactRepository.save(contact);

    }
}
