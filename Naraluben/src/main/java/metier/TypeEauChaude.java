package metier;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "type_eau_chaude")
public class TypeEauChaude {
    @Id
    @Column(name = "type_eau_chaude", nullable = false)
    private String typeEauChaude;

    @OneToMany(mappedBy = "typeEauChaude")
    private Set<Bien> biens = new LinkedHashSet<>();

    public TypeEauChaude(String typeEauChaude) {
        this.typeEauChaude = typeEauChaude;
    }

    public TypeEauChaude() {

    }

    public String getTypeEauChaude() {
        return typeEauChaude;
    }

    public void setTypeEauChaude(String typeEauChaude) {
        this.typeEauChaude = typeEauChaude;
    }

    public Set<Bien> getBiens() {
        return biens;
    }

    public void setBiens(Set<Bien> biens) {
        this.biens = biens;
    }
}