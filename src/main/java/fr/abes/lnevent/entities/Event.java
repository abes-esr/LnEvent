package fr.abes.lnevent.entities;


import fr.abes.lnevent.dto.etablissement.EtablissementDTO;
import fr.abes.lnevent.event.editeur.EditeurCreeEvent;
import fr.abes.lnevent.event.editeur.EditeurFusionneEvent;
import fr.abes.lnevent.event.editeur.EditeurModifieEvent;
import fr.abes.lnevent.event.etablissement.*;
import fr.abes.lnevent.event.ip.IpAjouteeEvent;
import fr.abes.lnevent.event.ip.IpModifieeEvent;
import fr.abes.lnevent.event.ip.IpSupprimeeEvent;
import fr.abes.lnevent.event.ip.IpValideeEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "EVENT_LN_EVENT")
@NoArgsConstructor
@Getter
public class Event {

    public Event(EtablissementCreeEvent etablissementCreeEvent) {
        this.event = "cree";
        this.nomEtab = etablissementCreeEvent.getNom();
        this.adresse = etablissementCreeEvent.getAdresse();
        this.siren = etablissementCreeEvent.getSiren();
        this.typeEtablissement = etablissementCreeEvent.getTypeEtablissement();
        this.motDePasse = etablissementCreeEvent.getMotDePasse();
        this.idAbes = etablissementCreeEvent.getIdAbes();
        this.mailContact = etablissementCreeEvent.getMailContact();
        this.nomContact = etablissementCreeEvent.getNomContact();
        this.prenomContact = etablissementCreeEvent.getPrenomContact();
        this.telephoneContact = etablissementCreeEvent.getTelephoneContact();
        this.adresseContact = etablissementCreeEvent.getAdresseContact();
    }

    public Event(EtablissementModifieEvent etablissementModifieEvent) {
        this.event = "modifie";
        this.nomEtab = etablissementModifieEvent.getNom();
        this.adresse = etablissementModifieEvent.getAdresse();
        this.siren = etablissementModifieEvent.getSiren();
        this.typeEtablissement = etablissementModifieEvent.getTypeEtablissement();
        this.motDePasse = etablissementModifieEvent.getMotDePasse();
        this.idAbes = etablissementModifieEvent.getIdAbes();
        this.mailContact = etablissementModifieEvent.getMailContact();
        this.nomContact = etablissementModifieEvent.getNomContact();
        this.prenomContact = etablissementModifieEvent.getPrenomContact();
        this.telephoneContact = etablissementModifieEvent.getTelephoneContact();
        this.adresseContact = etablissementModifieEvent.getAdresseContact();
    }

    public Event(EtablissementSupprimeEvent etablissementSupprimeEvent) {
        this.event = "supprime";
        this.nomEtab = etablissementSupprimeEvent.getSiren();
    }

    public Event(EtablissementDiviseEvent etablissementDiviseEvent) {
        this.event = "divise";
        this.ancienNomEtab = etablissementDiviseEvent.getAncienSiren();
        this.etablissementsDivise = etablissementDiviseEvent.getEtablissements();
    }

    public Event(EtablissementFusionneEvent etablissementFusionneEvent) {
        this.event = "fusionne";
        this.etablissementFusion = etablissementFusionneEvent.getEtablissement();
        this.etablissementsFusionne = etablissementFusionneEvent.getSirenFusionne();
    }

    public Event(EditeurCreeEvent editeurCreeEvent) {
        this.event = "editeurcree";
        this.nomEditeur = editeurCreeEvent.getNom();
        this.adresseEditeur = editeurCreeEvent.getAdresse();
        this.mailPourBatchEditeur = editeurCreeEvent.getMailPourBatch();
        this.mailPourInformationEditeur = editeurCreeEvent.getMailPourInformation();
    }

    public Event(EditeurModifieEvent editeurModifieEvent) {
        this.event = "editeurmodifie";
        this.nomEditeur = editeurModifieEvent.getNom();
        this.adresseEditeur = editeurModifieEvent.getAdresse();
        this.mailPourBatchEditeur = editeurModifieEvent.getMailPourBatch();
        this.mailPourInformationEditeur = editeurModifieEvent.getMailPourInformation();
    }

    public Event(EditeurFusionneEvent editeurFusionneEvent) {
        this.event = "editeurfusione";
        this.nomEditeur = editeurFusionneEvent.getEditeur().getNom();
        this.adresseEditeur = editeurFusionneEvent.getEditeur().getAdresse();
        this.mailPourBatchEditeur = editeurFusionneEvent.getEditeur().getMailPourBatch();
        this.mailPourInformationEditeur = editeurFusionneEvent.getEditeur().getMailPourInformation();
        this.idEditeurFusionnes = editeurFusionneEvent.getIdEditeurFusionnes();
    }

    public Event(IpAjouteeEvent ipAjouteeEvent) {
        this.event = "ipAjoute";
        this.ip = ipAjouteeEvent.getIp();
        this.siren = ipAjouteeEvent.getSiren();
    }

    public Event(IpModifieeEvent ipModifieeEvent) {
        this.event = "ipModifie";
        this.ip = ipModifieeEvent.getIp();
        this.siren = ipModifieeEvent.getSiren();
    }

    public Event(IpValideeEvent ipValideeEvent) {
        this.event = "ipValidee";
        this.ip = ipValideeEvent.getIp();
        this.siren = ipValideeEvent.getSiren();
    }

    public Event(IpSupprimeeEvent ipSupprimeeEvent) {
        this.event = "ipSupprimee";
        this.ip = ipSupprimeeEvent.getIp();
        this.siren = ipSupprimeeEvent.getSiren();
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String id;

    public String event;

    public String nomEtab;

    public String adresse;

    public String typeEtablissement;

    public String motDePasse;

    public String idAbes;

    public String mailContact;

    public String nomContact;

    public String prenomContact;

    public String telephoneContact;

    public String adresseContact;

    public String ancienNomEtab;

    public EtablissementDTO etablissementFusion;

    @ElementCollection(targetClass=String.class)
    public List<EtablissementDTO> etablissementsDivise;

    @ElementCollection(targetClass=String.class)
    public List<String> etablissementsFusionne;

    public String ip;

    public String siren;

    private String nomEditeur;

    private String adresseEditeur;

    @ElementCollection(targetClass=String.class)
    private List<String> mailPourBatchEditeur;

    @ElementCollection(targetClass=String.class)
    private List<String> mailPourInformationEditeur;

    @ElementCollection(targetClass=String.class)
    private List<String> idEditeurFusionnes;



}
