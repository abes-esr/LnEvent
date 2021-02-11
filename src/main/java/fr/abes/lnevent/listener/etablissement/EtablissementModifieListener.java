package fr.abes.lnevent.listener.etablissement;

import fr.abes.lnevent.event.etablissement.EtablissementModifieEvent;
import fr.abes.lnevent.repository.ContactRepository;
import fr.abes.lnevent.repository.EtablissementRepository;
import fr.abes.lnevent.entities.Contact;
import fr.abes.lnevent.entities.Etablissement;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EtablissementModifieListener implements ApplicationListener<EtablissementModifieEvent> {

    private final EtablissementRepository etablissementRepository;
    private final ContactRepository contactRepository;

    public EtablissementModifieListener(EtablissementRepository etablissementRepository,
                                     ContactRepository contactRepository) {
        this.etablissementRepository = etablissementRepository;
        this.contactRepository = contactRepository;
    }

    @Override
    public void onApplicationEvent(EtablissementModifieEvent etablissementModifieEvent) {
        Etablissement etablissement =
                new Etablissement(
                        etablissementModifieEvent.getIdEtablissement(),
                        etablissementModifieEvent.getNom(),
                        etablissementModifieEvent.getAdresse(),
                        etablissementModifieEvent.getSiren(),
                        etablissementModifieEvent.getTypeEtablissement(),
                        etablissementModifieEvent.getIdAbes());

        etablissementRepository.save(etablissement);

        Contact contact =
                new Contact(null,
                        etablissementModifieEvent.getNomContact(),
                        etablissementModifieEvent.getPrenomContact(),
                        etablissementModifieEvent.getMailContact(),
                        etablissementModifieEvent.getMotDePasse(),
                        etablissementModifieEvent.getTelephoneContact(),
                        etablissementModifieEvent.getAdresseContact(),
                        etablissementModifieEvent.getSiren());

        contactRepository.save(contact);
    }
}
