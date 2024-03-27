package metier;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "piece")
public class Piece {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "affectation_piece")
    private AffectationPiece affectationPiece;

    @Column(name = "surface")
    private Integer surface;

    @Column(name = "nb_murs")
    private Integer nbMurs;

    @Column(name = "nb_portes")
    private Integer nbPortes;

    @Column(name = "nb_fenetres")
    private Integer nbFenetres;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bien")
    private Bien bien;

    @OneToMany(mappedBy = "piece")
    private Set<Mobilier> mobiliers = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AffectationPiece getAffectationPiece() {
        return affectationPiece;
    }

    public void setAffectationPiece(AffectationPiece affectationPiece) {
        this.affectationPiece = affectationPiece;
    }

    public Integer getSurface() {
        return surface;
    }

    public void setSurface(Integer surface) {
        this.surface = surface;
    }

    public Integer getNbMurs() {
        return nbMurs;
    }

    public void setNbMurs(Integer nbMurs) {
        this.nbMurs = nbMurs;
    }

    public Integer getNbPortes() {
        return nbPortes;
    }

    public void setNbPortes(Integer nbPortes) {
        this.nbPortes = nbPortes;
    }

    public Integer getNbFenetres() {
        return nbFenetres;
    }

    public void setNbFenetres(Integer nbFenetres) {
        this.nbFenetres = nbFenetres;
    }

    public Bien getBien() {
        return bien;
    }

    public void setBien(Bien bien) {
        this.bien = bien;
    }

    public Set<Mobilier> getMobiliers() {
        return mobiliers;
    }

    public void setMobiliers(Set<Mobilier> mobiliers) {
        this.mobiliers = mobiliers;
    }

}