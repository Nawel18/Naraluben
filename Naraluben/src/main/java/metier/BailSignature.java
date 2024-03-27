package metier;

import jakarta.persistence.*;

@Entity
@Table(name = "bail_signature")
public class BailSignature {
    @EmbeddedId
    private BailSignatureId id;

    @MapsId("signature")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "signature", nullable = false)
    private Signature signature;

    @MapsId("bail")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "bail", nullable = false)
    private Bail bail;

    public BailSignatureId getId() {
        return id;
    }

    public void setId(BailSignatureId id) {
        this.id = id;
    }

    public Signature getSignature() {
        return signature;
    }

    public void setSignature(Signature signature) {
        this.signature = signature;
    }

    public Bail getBail() {
        return bail;
    }

    public void setBail(Bail bail) {
        this.bail = bail;
    }

}