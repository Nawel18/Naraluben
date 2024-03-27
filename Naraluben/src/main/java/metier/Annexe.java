package metier;

import jakarta.persistence.*;

@Entity
@Table(name = "annexe")
public class Annexe {
    @Id
    @Column(name = "no_annexe", nullable = false)
    private Integer id;

    @Column(name = "surface")
    private Integer surface;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bien")
    private Bien bien;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bien getBien() {
        return bien;
    }

    public void setBien(Bien bien) {
        this.bien = bien;
    }

}