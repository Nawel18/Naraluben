package metier;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "type_chauffage")
public class TypeChauffage {
    @Id
    @Column(name = "type_chauffage", nullable = false)
    private String typeChauffage;

    @OneToMany(mappedBy = "typeChauffage")
    private Set<Bien> biens = new LinkedHashSet<>();

    public String getTypeChauffage() {
        return typeChauffage;
    }

    public void setTypeChauffage(String typeChauffage) {
        this.typeChauffage = typeChauffage;
    }

    public Set<Bien> getBiens() {
        return biens;
    }

    public void setBiens(Set<Bien> biens) {
        this.biens = biens;
    }

}