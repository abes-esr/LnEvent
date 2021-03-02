package fr.abes.lnevent.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Etablissement")
@NoArgsConstructor
@Setter
@Getter
public class EtablissementEntity {



    public EtablissementEntity(Long id, String name, String adresse, String siren, String motDePasse, String typeEtablissement, String idAbes, ContactEntity contact, Set<EditeurEntity> editeurs) {
        this.id = id;
        this.name = name;
        this.adresse = adresse;
        this.siren = siren;
        this.motDePasse = motDePasse;
        this.typeEtablissement = typeEtablissement;
        this.idAbes = idAbes;
        this.contact = contact;
        this.editeurs = editeurs;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "etablissement_Sequence")
    @SequenceGenerator(name = "etablissement_Sequence", sequenceName = "ETABLISSEMENT_SEQ", allocationSize = 1)
    private Long id;

    private String name;

    private String adresse;

    private String siren;

    private String motDePasse;

    private String typeEtablissement;

    private boolean valide;

    private String idAbes;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private ContactEntity contact;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnore
    private Set<IpEntity> ips = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "etablissement_editeur",
            joinColumns = @JoinColumn(name = "etablissement_id"),
            inverseJoinColumns = @JoinColumn(name = "editeur_id"))
    private Set<EditeurEntity> editeurs = new HashSet<>();
}