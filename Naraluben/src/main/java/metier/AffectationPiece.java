package metier;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "affectation_piece")
public class AffectationPiece {
    @Id
    @Column(name = "affectation_piece", nullable = false)
    private String affectationPiece;

    @OneToMany(mappedBy = "affectationPiece")
    private Set<Piece> pieces = new LinkedHashSet<>();

    public String getAffectationPiece() {
        return affectationPiece;
    }

    public void setAffectationPiece(String affectationPiece) {
        this.affectationPiece = affectationPiece;
    }

    public Set<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(Set<Piece> pieces) {
        this.pieces = pieces;
    }

}