package metier;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BailSignatureId implements Serializable {
    private static final long serialVersionUID = -4325575916006669244L;
    @Column(name = "signature", nullable = false)
    private Integer signature;

    @Column(name = "bail", nullable = false)
    private Integer bail;

    public Integer getSignature() {
        return signature;
    }

    public void setSignature(Integer signature) {
        this.signature = signature;
    }

    public Integer getBail() {
        return bail;
    }

    public void setBail(Integer bail) {
        this.bail = bail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BailSignatureId entity = (BailSignatureId) o;
        return Objects.equals(this.signature, entity.signature) &&
                Objects.equals(this.bail, entity.bail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(signature, bail);
    }

}