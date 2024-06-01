package metier;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "bien")
public class Bien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "no_bien", nullable = false)
    private Integer id;

    @Column(name = "surface")
    private Integer surface;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adresse")
    private Adresse adresse;

    @Column(name = "no_logement")
    private Integer noLogement;

    @Column(name = "etage")
    private Integer etage;

    @Column(name = "date_creation")
    private LocalDate dateCreation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_bien")
    private TypeBien typeBien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "classification_bien")
    private ClassificationBien classificationBien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_chauffage")
    private TypeChauffage typeChauffage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_eau_chaude")
    private TypeEauChaude typeEauChaude;

    @Column(name = "nb_pieces")
    private Integer nbPieces;

    @Column(name = "meuble")
    private Boolean meuble;

    @Column(name = "situation")
    private Integer situation;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "bien")
    private Set<Annexe> annexes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "bien")
    private Set<Bail> bails = new LinkedHashSet<>();

    @OneToMany(mappedBy = "bien")
    private Set<BienProprietaire> bienProprietaires = new LinkedHashSet<>();

    @OneToMany(mappedBy = "bien")
    private Set<Piece> pieces = new LinkedHashSet<>();

    public Bien(Integer surface, Integer noLogement, Integer etage, TypeBien typeBien, ClassificationBien classificationBien, TypeChauffage typeChauffage, TypeEauChaude typeEauChaude, Integer nbPieces, Boolean meuble, Integer situation, String description, String image) {
        this.surface = surface;
        this.noLogement = noLogement;
        this.etage = etage;
        this.typeBien = typeBien;
        this.classificationBien = classificationBien;
        this.typeChauffage = typeChauffage;
        this.typeEauChaude = typeEauChaude;
        this.nbPieces = nbPieces;
        this.meuble = meuble;
        this.situation = situation;
        this.description = description;
        this.image = image;
    }

    public Bien() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSurface() {
        return surface;
    }

    public void setSurface(Integer surface) {
        this.surface = surface;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Integer getNoLogement() {
        return noLogement;
    }

    public void setNoLogement(Integer noLogement) {
        this.noLogement = noLogement;
    }

    public Integer getEtage() {
        return etage;
    }

    public void setEtage(Integer etage) {
        this.etage = etage;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public TypeBien getTypeBien() {
        return typeBien;
    }

    public void setTypeBien(TypeBien typeBien) {
        this.typeBien = typeBien;
    }

    public ClassificationBien getClassificationBien() {
        return classificationBien;
    }

    public void setClassificationBien(ClassificationBien classificationBien) {
        this.classificationBien = classificationBien;
    }

    public TypeChauffage getTypeChauffage() {
        return typeChauffage;
    }

    public void setTypeChauffage(TypeChauffage typeChauffage) {
        this.typeChauffage = typeChauffage;
    }

    public TypeEauChaude getTypeEauChaude() {
        return typeEauChaude;
    }

    public void setTypeEauChaude(TypeEauChaude typeEauChaude) {
        this.typeEauChaude = typeEauChaude;
    }

    public Integer getNbPieces() {
        return nbPieces;
    }

    public void setNbPieces(Integer nbPieces) {
        this.nbPieces = nbPieces;
    }

    public Boolean getMeuble() {
        return meuble;
    }

    public void setMeuble(Boolean meuble) {
        this.meuble = meuble;
    }

    public Integer getSituation() {
        return situation;
    }

    public void setSituation(Integer situation) {
        this.situation = situation;
    }

    public Set<Annexe> getAnnexes() {
        return annexes;
    }

    public void setAnnexes(Set<Annexe> annexes) {
        this.annexes = annexes;
    }

    public Set<Bail> getBails() {
        return bails;
    }

    public void setBails(Set<Bail> bails) {
        this.bails = bails;
    }

    public Set<BienProprietaire> getBienProprietaires() {
        return bienProprietaires;
    }

    public void setBienProprietaires(Set<BienProprietaire> bienProprietaires) {
        this.bienProprietaires = bienProprietaires;
    }

    public Set<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(Set<Piece> pieces) {
        this.pieces = pieces;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Bien{" +
                "id=" + id +
                ", surface=" + surface +
                ", adresse=" + adresse +
                ", noLogement=" + noLogement +
                ", etage=" + etage +
                ", dateCreation=" + dateCreation +
                ", typeBien=" + typeBien +
                ", classificationBien=" + classificationBien +
                ", typeChauffage=" + typeChauffage +
                ", typeEauChaude=" + typeEauChaude +
                ", nbPieces=" + nbPieces +
                ", meuble=" + meuble +
                ", situation=" + situation +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", annexes=" + annexes +
                ", bails=" + bails +
                ", bienProprietaires=" + bienProprietaires +
                ", pieces=" + pieces +
                '}';
    }
}