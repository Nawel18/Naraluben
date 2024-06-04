package metier;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "tiers")
public class Tiers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no_tiers", nullable = false)
    private Integer id;

    @Column(name = "nom", length = 50)
    private String nom;

    @Column(name = "prenom", length = 50)
    private String prenom;

    @Column(name = "date_de_naissance")
    private LocalDate dateDeNaissance;

    @Column(name = "no_ss", length = 13)
    private String noSs;

    @Column(name = "rib", length = 34)
    private String rib;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "noTiers")
    private Set<Agent> agents = new LinkedHashSet<>();

    @OneToMany(mappedBy = "noTiers")
    private Set<Locataire> locataires = new LinkedHashSet<>();

    @OneToMany(mappedBy = "noTiers")
    private Set<Proprietaire> proprietaires = new LinkedHashSet<>();

    @OneToMany(mappedBy = "signataire")
    private Set<Signature> signatures = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getNoSs() {
        return noSs;
    }

    public void setNoSs(String noSs) {
        this.noSs = noSs;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Agent> getAgents() {
        return agents;
    }

    public void setAgents(Set<Agent> agents) {
        this.agents = agents;
    }

    public Set<Locataire> getLocataires() {
        return locataires;
    }

    public void setLocataires(Set<Locataire> locataires) {
        this.locataires = locataires;
    }

    public Set<Proprietaire> getProprietaires() {
        return proprietaires;
    }

    public void setProprietaires(Set<Proprietaire> proprietaires) {
        this.proprietaires = proprietaires;
    }

    public Set<Signature> getSignatures() {
        return signatures;
    }

    public void setSignatures(Set<Signature> signatures) {
        this.signatures = signatures;
    }

}