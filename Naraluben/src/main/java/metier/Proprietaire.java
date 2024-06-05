package metier;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "proprietaire")
public class Proprietaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "no_tiers")
    private Tiers noTiers;

    @OneToMany(mappedBy = "proprietaire")
    private Set<BienProprietaire> bienProprietaires = new LinkedHashSet<>();

    public Proprietaire(Tiers noTiers) {
        this.noTiers = noTiers;
    }

    public Proprietaire() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tiers getNoTiers() {
        return noTiers;
    }

    public void setNoTiers(Tiers noTiers) {
        this.noTiers = noTiers;
    }

    public Set<BienProprietaire> getBienProprietaires() {
        return bienProprietaires;
    }

    public void setBienProprietaires(Set<BienProprietaire> bienProprietaires) {
        this.bienProprietaires = bienProprietaires;
    }
}