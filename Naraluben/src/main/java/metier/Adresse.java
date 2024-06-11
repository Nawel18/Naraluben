package metier;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "adresse")
public class Adresse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "no_dans_la_rue", length = 5)
    private String noDansLaRue;

    @Column(name = "nom_rue")
    private String nomRue;

    @Column(name = "ville")
    private String ville;

    @OneToMany(mappedBy = "adresse")
    private Set<Bien> biens = new LinkedHashSet<>();

    public Adresse(String noDansLaRue, String nomRue, String ville) {
        this.noDansLaRue = noDansLaRue;
        this.nomRue = nomRue;
        this.ville = ville;
    }

    public Adresse() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoDansLaRue() {
        return noDansLaRue;
    }

    public void setNoDansLaRue(String noDansLaRue) {
        this.noDansLaRue = noDansLaRue;
    }

    public String getNomRue() {
        return nomRue;
    }

    public void setNomRue(String nomRue) {
        this.nomRue = nomRue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Set<Bien> getBiens() {
        return biens;
    }

    public void setBiens(Set<Bien> biens) {
        this.biens = biens;
    }
}