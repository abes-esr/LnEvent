package fr.abes.lnevent.listener.etablissement;

import fr.abes.lnevent.dto.etablissement.EtablissementDTO;
import fr.abes.lnevent.repository.ContactRepository;
import fr.abes.lnevent.entities.Contact;
import fr.abes.lnevent.entities.Etablissement;
import fr.abes.lnevent.repository.EtablissementRepository;
import fr.abes.lnevent.event.etablissement.EtablissementDiviseEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class EtablissementDiviseListener implements ApplicationListener<EtablissementDiviseEvent> {

    private final EtablissementRepository etablissementRepository;
    private final ContactRepository contactRepository;

    public EtablissementDiviseListener(EtablissementRepository etablissementRepository, ContactRepository contactRepository) {
        this.etablissementRepository = etablissementRepository;
        this.contactRepository = contactRepository;
    }

    @Override
    public void onApplicationEvent(EtablissementDiviseEvent etablissementDiviseEvent) {
        etablissementRepository.deleteBySiren(etablissementDiviseEvent.getAncienSiren());
        contactRepository.deleteBySiren(etablissementDiviseEvent.getAncienSiren());
        for (EtablissementDTO etablissementDivise :
                etablissementDiviseEvent.getEtablissements()) {
            etablissementRepository.save(new Etablissement(null,
                    etablissementDivise.getNom(),
                    etablissementDivise.getAdresse(),
                    etablissementDivise.getSiren(),
                    etablissementDivise.getTypeEtablissement(),
                    etablissementDivise.getIdAbes()));

            Contact contact =
                    new Contact(null,
                            etablissementDivise.getNomContact(),
                            etablissementDivise.getPrenomContact(),
                            etablissementDivise.getMailContact(),
                            etablissementDivise.getMotDePasse(),
                            etablissementDivise.getTelephoneContact(),
                            etablissementDivise.getAdresseContact(),
                            etablissementDivise.getSiren());

            contactRepository.save(contact);
        }
    }
}
