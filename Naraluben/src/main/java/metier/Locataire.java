package metier;

import jakarta.persistence.*;

@Entity
@Table(name = "locataire")
public class Locataire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "no_tiers")
    private Tier noTiers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tier getNoTiers() {
        return noTiers;
    }

    public void setNoTiers(Tier noTiers) {
        this.noTiers = noTiers;
    }

}