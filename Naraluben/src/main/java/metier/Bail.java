package metier;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "bail")
public class Bail {
    @Id
    @Column(name = "no_bail", nullable = false)
    private Integer id;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "etat_sortie")
    private LocalDate etatSortie;

    @Column(name = "etat_entree")
    private LocalDate etatEntree;

    @Column(name = "nb_jeux_de_cles")
    private Integer nbJeuxDeCles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bien")
    private Bien bien;

    @ManyToMany
    @JoinTable(name = "bail_signature",
            joinColumns = @JoinColumn(name = "bail"),
            inverseJoinColumns = @JoinColumn(name = "signature"))
    private Set<Signature> signatures = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getEtatSortie() {
        return etatSortie;
    }

    public void setEtatSortie(LocalDate etatSortie) {
        this.etatSortie = etatSortie;
    }

    public LocalDate getEtatEntree() {
        return etatEntree;
    }

    public void setEtatEntree(LocalDate etatEntree) {
        this.etatEntree = etatEntree;
    }

    public Integer getNbJeuxDeCles() {
        return nbJeuxDeCles;
    }

    public void setNbJeuxDeCles(Integer nbJeuxDeCles) {
        this.nbJeuxDeCles = nbJeuxDeCles;
    }

    public Bien getBien() {
        return bien;
    }

    public void setBien(Bien bien) {
        this.bien = bien;
    }

    public Set<Signature> getSignatures() {
        return signatures;
    }

    public void setSignatures(Set<Signature> signatures) {
        this.signatures = signatures;
    }

}