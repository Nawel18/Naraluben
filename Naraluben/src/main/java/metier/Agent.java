package metier;

import jakarta.persistence.*;

@Entity
@Table(name = "agent")
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "no_tiers")
    private Tiers noTiers;

    public Agent(Tiers noTiers) {
        this.noTiers = noTiers;
    }

    public Agent() {

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
}