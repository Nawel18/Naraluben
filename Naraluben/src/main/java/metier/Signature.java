package metier;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "signature")
public class Signature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "date_signature")
    private LocalDate dateSignature;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "signataire")
    private Tier signataire;

    @ManyToMany(mappedBy = "signatures")
    private Set<Bail> bails = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateSignature() {
        return dateSignature;
    }

    public void setDateSignature(LocalDate dateSignature) {
        this.dateSignature = dateSignature;
    }

    public Tier getSignataire() {
        return signataire;
    }

    public void setSignataire(Tier signataire) {
        this.signataire = signataire;
    }

    public Set<Bail> getBails() {
        return bails;
    }

    public void setBails(Set<Bail> bails) {
        this.bails = bails;
    }

}