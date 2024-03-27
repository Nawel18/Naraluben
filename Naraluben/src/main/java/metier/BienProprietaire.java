package metier;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "bien_proprietaire")
public class BienProprietaire {
    @EmbeddedId
    private BienProprietaireId id;

    @MapsId("bien")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bien", nullable = false)
    private Bien bien;

    @MapsId("proprietaire")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "proprietaire", nullable = false)
    private Proprietaire proprietaire;

    @Column(name = "date_debut")
    private LocalDate dateDebut;

    @Column(name = "date_fin")
    private LocalDate dateFin;

    public BienProprietaireId getId() {
        return id;
    }

    public void setId(BienProprietaireId id) {
        this.id = id;
    }

    public Bien getBien() {
        return bien;
    }

    public void setBien(Bien bien) {
        this.bien = bien;
    }

    public Proprietaire getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Proprietaire proprietaire) {
        this.proprietaire = proprietaire;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

}