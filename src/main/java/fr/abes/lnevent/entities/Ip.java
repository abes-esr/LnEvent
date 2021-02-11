package fr.abes.lnevent.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Entity
@Table(name = "IP_LN_EVENT")
@Getter
@NoArgsConstructor
public class Ip {

    public Ip(String id, String ip, String siren) {
        this.id = id;
        this.ip = ip;
        this.siren = siren;
        this.validee = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String id;

    public String ip;

    public String siren;

    public boolean validee;
}
