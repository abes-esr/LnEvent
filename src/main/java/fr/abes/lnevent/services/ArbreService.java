package fr.abes.lnevent.services;

import fr.abes.lnevent.dto.etablissement.EtablissementDTO;
import fr.abes.lnevent.entities.Event;
import fr.abes.lnevent.entities.Ip;
import fr.abes.lnevent.repository.EventRepository;
import fr.abes.lnevent.repository.IpRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArbreService {
    private EventRepository eventRepository;
    private IpRepository ipRepository;

    public ArbreService(EventRepository eventRepository, IpRepository ipRepository) {
        this.eventRepository = eventRepository;
        this.ipRepository = ipRepository;
    }

    public String genereArbre() {
        StringBuilder builder = new StringBuilder();
        for (Event event :
                eventRepository.findAll()) {
            switch (event.event) {
                case "cree":
                    builder.append("Nouveau : ").append(event.nomEtab).append("\n");
                    List<Ip> ips = ipRepository.findAllBySiren(event.siren);
                    for (Ip ip :
                            ips) {
                        builder.append(ip.ip).append("\n");
                    }
                    break;
                case "supprime":
                    builder.append("Supprimer : ").append(event.nomEtab).append("\n");
                    break;
                case "divise":
                    builder.append("Divise : ").append(event.ancienNomEtab).append("\n");
                    for (EtablissementDTO etab :
                            event.etablissementsDivise) {
                        builder.append(etab.getNom()).append("\n");
                    }
                    break;
                case "fusionne":
                    builder.append("Fusion : ").append(event.nomEtab).append("\n");
                    for (String etab :
                            event.etablissementsFusionne) {
                        builder.append(etab).append("\n");
                    }
                    break;
            }
        }
        return builder.toString();
    }
}
