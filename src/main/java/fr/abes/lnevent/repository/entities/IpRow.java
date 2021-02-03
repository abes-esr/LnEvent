package fr.abes.lnevent.repository.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Ip")
public class IpRow {

    public IpRow(String id, String ip, String siren) {
        this.id = id;
        this.ip = ip;
        this.siren = siren;
        this.validee = false;
    }

    public IpRow(String id, String ip, String siren, Date dateAjout) {
        this.id = id;
        this.ip = ip;
        this.siren = siren;
        this.dateAjout = dateAjout;
        this.validee = false;
    }

    @Id
    public String id;

    public String ip;

    public String siren;

    public boolean validee;

    public Date dateAjout;
}
