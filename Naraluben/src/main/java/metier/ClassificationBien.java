package metier;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "classification_bien")
public class ClassificationBien {
    @Id
    @Column(name = "classification_bien", nullable = false)
    private String classificationBien;

    public String getClassificationBien() {
        return classificationBien;
    }

    public void setClassificationBien(String classificationBien) {
        this.classificationBien = classificationBien;
    }

    //TODO [JPA Buddy] generate columns from DB
}