package metier;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "type_bien")
public class TypeBien {
    @Id
    @Column(name = "type_bien", nullable = false)
    private String typeBien;

    @OneToMany(mappedBy = "typeBien")
    private Set<Bien> biens = new LinkedHashSet<>();

    public TypeBien(String typeBien) {
        this.typeBien = typeBien;
    }

    public TypeBien() {

    }

    public String getTypeBien() {
        return typeBien;
    }

    public void setTypeBien(String typeBien) {
        this.typeBien = typeBien;
    }

    public Set<Bien> getBiens() {
        return biens;
    }

    public void setBiens(Set<Bien> biens) {
        this.biens = biens;
    }
}