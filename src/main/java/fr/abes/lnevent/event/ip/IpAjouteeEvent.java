package fr.abes.lnevent.event.ip;

import fr.abes.lnevent.event.Event;
import lombok.Getter;

import java.util.Date;

@Getter
public class IpAjouteeEvent extends Event {

    private String ip;

    private String siren;


    private Date dateAjout;

    public IpAjouteeEvent(Object source, String ip, String siren, Date dateAjout) {
        super(source);
        this.ip = ip;
        this.siren = siren;
        this.dateAjout = dateAjout;
    }
}
