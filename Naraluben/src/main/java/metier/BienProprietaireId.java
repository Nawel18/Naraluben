package metier;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BienProprietaireId implements Serializable {
    private static final long serialVersionUID = 5004947822491207126L;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bien", nullable = false)
    private Bien bien;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "proprietaire", nullable = false)
    private Proprietaire proprietaire;

    public BienProprietaireId(Bien bien, Proprietaire proprietaire) {
        this.bien = bien;
        this.proprietaire = proprietaire;
    }

    public BienProprietaireId() {

    }

    public Bien getBien() {
        return bien;
    }

    public void setBien(Bien bien) {
        this.bien = bien;
    }

    public Proprietaire getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Proprietaire proprietaire) {
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