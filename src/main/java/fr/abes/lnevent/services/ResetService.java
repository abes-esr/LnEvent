package fr.abes.lnevent.services;

import fr.abes.lnevent.dto.etablissement.EtablissementDTO;
import fr.abes.lnevent.event.etablissement.EtablissementCreeEvent;
import fr.abes.lnevent.entities.Event;
import fr.abes.lnevent.repository.EtablissementRepository;
import fr.abes.lnevent.repository.EventRepository;
import fr.abes.lnevent.event.etablissement.EtablissementDiviseEvent;
import fr.abes.lnevent.event.etablissement.EtablissementFusionneEvent;
import fr.abes.lnevent.event.etablissement.EtablissementSupprimeEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ResetService {
    private EtablissementRepository etablissementRepository;
    private EventRepository eventRepository;
    private ApplicationEventPublisher applicationEventPublisher;

    public ResetService(EtablissementRepository etablissementRepository, EventRepository eventRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.etablissementRepository = etablissementRepository;
        this.eventRepository = eventRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public String resetEtablissement() {
        etablissementRepository.deleteAll();
        for (Event event :
                eventRepository.findAll()) {
            switch (event.event) {
                case "cree":
                    EtablissementCreeEvent etablissementCreeEvent =
                            new EtablissementCreeEvent(this,
                                    event.nomEtab,
                                    event.adresse,
                                    event.siren,
                                    event.typeEtablissement,
                                    event.motDePasse,
                                    event.idAbes,
                                    event.mailContact,
                                    event.nomContact,
                                    event.prenomContact,
                                    event.telephoneContact,
                                    event.adresseContact);
                    applicationEventPublisher.publishEvent(etablissementCreeEvent);
                    break;
                case "supprime":
                    EtablissementSupprimeEvent etablissementSupprimeEvent =
                            new EtablissementSupprimeEvent(this, event.nomEtab);
                    applicationEventPublisher.publishEvent(etablissementSupprimeEvent);
                    break;
                case "divise":
                    EtablissementDiviseEvent etablissementDiviseEvent =
                            new EtablissementDiviseEvent(
                                    this,
                                    event.ancienNomEtab,
                                    (ArrayList<EtablissementDTO>) event.etablissementsDivise);
                    applicationEventPublisher.publishEvent(etablissementDiviseEvent);
                    break;
                case "fusionne":
                    EtablissementFusionneEvent etablissementFusionneEvent =
                            new EtablissementFusionneEvent(this,
                                    event.etablissementFusion,
                                    (ArrayList<String>) event.etablissementsFusionne);
                    applicationEventPublisher.publishEvent(etablissementFusionneEvent);
                    break;
            }
        }
        return "done";
    }
}
