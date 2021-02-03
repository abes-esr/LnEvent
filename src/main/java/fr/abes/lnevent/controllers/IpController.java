package fr.abes.lnevent.controllers;

import fr.abes.lnevent.dto.ip.IpAjouteeDTO;
import fr.abes.lnevent.dto.ip.IpModifieeDTO;
import fr.abes.lnevent.dto.ip.IpSupprimeeDTO;
import fr.abes.lnevent.dto.ip.IpValideeDTO;
import fr.abes.lnevent.event.ip.IpAjouteeEvent;
import fr.abes.lnevent.event.ip.IpModifieeEvent;
import fr.abes.lnevent.event.ip.IpSupprimeeEvent;
import fr.abes.lnevent.event.ip.IpValideeEvent;
import fr.abes.lnevent.repository.EventRepository;
import fr.abes.lnevent.repository.IpRepository;
import fr.abes.lnevent.repository.entities.EventRow;
import fr.abes.lnevent.repository.entities.IpRow;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Log
@RestController
@RequestMapping("ln/ip")
public class IpController {
    @Autowired
    private EventRepository repository;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private IpRepository ipRepository;

    @PostMapping(value = "/ajout")
    public String ajoutIp(@RequestBody IpAjouteeDTO event) {
        IpAjouteeEvent ipAjouteeEvent = new IpAjouteeEvent(this,
                event.getIp(),
                event.getSiren(),
                new Date());
        applicationEventPublisher.publishEvent(ipAjouteeEvent);
        repository.save(new EventRow(ipAjouteeEvent));

        return "done";
    }

    @PostMapping(value = "/modifie")
    public String edit(@RequestBody IpModifieeDTO ipModifieeDTO) {
        IpModifieeEvent ipModifieeEvent = new IpModifieeEvent(this,
                ipModifieeDTO.getId(),
                ipModifieeDTO.getIp(),
                ipModifieeDTO.getSiren());
        applicationEventPublisher.publishEvent(ipModifieeEvent);
        repository.save(new EventRow(ipModifieeEvent));
        return "done";
    }

    @PostMapping(value = "/valide")
    public String validate(@RequestBody IpValideeDTO ipValideeDTO) {
        IpValideeEvent ipValideeEvent = new IpValideeEvent(this,
                ipValideeDTO.getIp(),
                ipValideeDTO.getSiren());
        applicationEventPublisher.publishEvent(ipValideeEvent);
        repository.save(new EventRow(ipValideeEvent));
        return "done";
    }

    @PostMapping(value = "/supprime")
    public String delete(@RequestBody IpSupprimeeDTO ipSupprimeeDTO) {
        IpSupprimeeEvent ipSupprimeeEvent = new IpSupprimeeEvent(this,
                ipSupprimeeDTO.getIp(),
                ipSupprimeeDTO.getSiren());
        applicationEventPublisher.publishEvent(ipSupprimeeEvent);
        repository.save(new EventRow(ipSupprimeeEvent));
        return "done";
    }

    @GetMapping(value = "/listIpParSiren/{siren}")
    public List<IpRow> getIpBySiren(@PathVariable String siren) {
        return ipRepository.findAllBySiren(siren);
    }


}
