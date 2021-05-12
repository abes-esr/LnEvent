package fr.abes.lnevent.dto.ip;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter @Setter
public class Ipv4AjouteeDTO extends IpAjouteeDTO{

    @NotBlank(message="L'IP est obligatoire")
    @Pattern(regexp = "\\b((([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])(\\.)){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5]))\\b", message = "L'IP fournie n'est pas valide")
    private String ip;

    public Ipv4AjouteeDTO(String siren, String typeIp, String ip, String typeAcces, String commentaires) {
        super(siren, typeIp, ip, typeAcces, commentaires);
        this.ip = ip;
    }
}
