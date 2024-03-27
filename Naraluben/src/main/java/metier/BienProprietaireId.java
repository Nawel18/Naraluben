package metier;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BienProprietaireId implements Serializable {
    private static final long serialVersionUID = 5004947822491207126L;
    @Column(name = "bien", nullable = false)
    private Integer bien;

    @Column(name = "proprietaire", nullable = false)
    private Integer proprietaire;

    public Integer getBien() {
        return bien;
    }

    public void setBien(Integer bien) {
        this.bien = bien;
    }

    public Integer getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Integer proprietaire) {
        this.proprietaire = proprietaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BienProprietaireId entity = (BienProprietaireId) o;
        return Objects.equals(this.proprietaire, entity.proprietaire) &&
                Objects.equals(this.bien, entity.bien);
    }

    @Override
    public int hashCode() {
        return Objects.hash(proprietaire, bien);
    }

}