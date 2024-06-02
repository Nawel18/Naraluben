package vues;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jpaDao.JpaDaoAdresse;
import jpaDao.JpaDaoBien;
import metier.*;
import utils.ButtonsUtil;

import java.io.File;
import java.io.FileNotFoundException;

public class VueModifierBien {
    private Stage stage;
    private Scene scene;

    public VueModifierBien(Stage stage, Bien bien, JpaDaoBien jpa) {
        this.stage = stage;

        //Titre de la vue
        Label titre = new Label("Modification du bien");
        titre.setStyle("-fx-font: 30 arial;-fx-text-fill: #5693bd;-fx-padding: 30px;");

        Button boutonGoBack = ButtonsUtil.createGoBackButton(this.stage);

        //Container pour le formulaire
        VBox form = new VBox();
        form.setStyle("-fx-padding: 50px;");
        form.setSpacing(15);

        //adresse
        Label labelAdresse = new Label("Adresse : ");
        labelAdresse.setStyle("-fx-font: 16 arial;");

        TextField fieldNoRue = new TextField();
        fieldNoRue.setMinWidth(20);
        fieldNoRue.setText(bien.getAdresse().getNoDansLaRue());

        TextField fieldRue = new TextField();
        fieldRue.setMinWidth(300);
        fieldRue.setText(bien.getAdresse().getNomRue());

        TextField fieldVille = new TextField();
        fieldVille.setMinWidth(300);
        fieldVille.setText(bien.getAdresse().getVille());

        HBox adresse = new HBox(labelAdresse, fieldNoRue, fieldRue, fieldVille);
        form.getChildren().add(adresse);


        //No logement
        Label labelNoLogement = new Label("N° logement : ");
        labelNoLogement.setStyle("-fx-font: 16 arial;");

        TextField fieldNoLogement = new TextField();
        fieldNoLogement.setMinWidth(300);
        fieldNoLogement.setText(String.valueOf(bien.getNoLogement()));
        HBox noLogement = new HBox(labelNoLogement, fieldNoLogement);
        form.getChildren().add(noLogement);

        //Etage
        Label labelEtage = new Label("Etage : ");
        labelEtage.setStyle("-fx-font: 16 arial;");

        TextField fieldEtage = new TextField();
        fieldEtage.setMinWidth(300);
        fieldEtage.setText(String.valueOf(bien.getEtage()));

        HBox etage = new HBox(labelEtage, fieldEtage);
        form.getChildren().add(etage);

        //surface
        Label labelSurface = new Label("Surface (en m²) : ");
        labelSurface.setStyle("-fx-font: 16 arial;");

        TextField fieldSurface = new TextField();
        fieldSurface.setMinWidth(300);
        fieldSurface.setText(String.valueOf(bien.getSurface()));

        HBox surface = new HBox(labelSurface, fieldSurface);
        form.getChildren().add(surface);

        //nb pieces
        Label labelNbPieces = new Label("Nombre de pièces : ");
        labelNbPieces.setStyle("-fx-font: 16 arial;");

        TextField fieldNbPieces = new TextField();
        fieldNbPieces.setMinWidth(300);
        fieldNbPieces.setText(String.valueOf(bien.getNbPieces()));

        HBox nbPieces = new HBox(labelNbPieces, fieldNbPieces);
        form.getChildren().add(nbPieces);

        //meublé
        Label labelMeuble = new Label("Logement meublé : ");
        labelMeuble.setStyle("-fx-font: 16 arial;");

        CheckBox checkboxMeuble = new CheckBox();
        if (bien.getMeuble()) checkboxMeuble.setSelected(true);

        HBox meuble = new HBox(labelMeuble, checkboxMeuble);
        form.getChildren().add(meuble);

        //type de bien
        Label labelTypeBien = new Label("Type de bien : ");
        labelTypeBien.setStyle("-fx-font: 16 arial;");

        ChoiceBox selectTypeBien = new ChoiceBox();
        selectTypeBien.setMinWidth(300);
        selectTypeBien.getItems().addAll(metier.enums.TypeBien.values());
        HBox typeBien = new HBox(labelTypeBien, selectTypeBien);
        if (bien.getTypeBien() != null) selectTypeBien.setValue(bien.getTypeBien().getTypeBien());
        form.getChildren().add(typeBien);

        //classification du bien
        Label labelClassificationBien = new Label("Classification du bien : ");
        labelClassificationBien.setStyle("-fx-font: 16 arial;");

        ChoiceBox selectClassificationBien = new ChoiceBox();
        selectClassificationBien.setMinWidth(300);
        selectClassificationBien.getItems().addAll(metier.enums.ClassificationBien.values());
        if (bien.getClassificationBien() != null)
            selectClassificationBien.setValue(bien.getClassificationBien().getClassificationBien());

        HBox classificationBien = new HBox(labelClassificationBien, selectClassificationBien);
        form.getChildren().add(classificationBien);

        //type chauffage
        Label labelTypeChauffage = new Label("Type de chauffage : ");
        labelTypeChauffage.setStyle("-fx-font: 16 arial;");

        ChoiceBox selectTypeChauffage = new ChoiceBox();
        selectTypeChauffage.setMinWidth(300);
        selectTypeChauffage.getItems().addAll(metier.enums.TypeChauffage.values());
        if (bien.getTypeEauChaude() != null) selectTypeChauffage.setValue(bien.getTypeChauffage().getTypeChauffage());

        HBox typeChauffage = new HBox(labelTypeChauffage, selectTypeChauffage);
        form.getChildren().add(typeChauffage);

        //type eau chaude
        Label labelEauChaude = new Label("Type eau chaude : ");
        labelEauChaude.setStyle("-fx-font: 16 arial;");

        ChoiceBox selectEauChaude = new ChoiceBox();
        selectEauChaude.setMinWidth(300);
        selectEauChaude.getItems().addAll(metier.enums.TypeEauChaude.values());
        if (bien.getTypeEauChaude() != null) selectEauChaude.setValue(bien.getTypeEauChaude().getTypeEauChaude());

        HBox eauChaude = new HBox(labelEauChaude, selectEauChaude);
        form.getChildren().add(eauChaude);

        //description
        Label labelDescription = new Label("Description : ");
        labelDescription.setStyle("-fx-font: 16 arial;");

        TextArea fieldDescription = new TextArea();
        fieldDescription.setMinWidth(300);
        fieldDescription.setText(bien.getDescription());

        HBox description = new HBox(labelDescription, fieldDescription);
        form.getChildren().add(description);

        //image
        Label labelImage = new Label("Image : ");
        labelImage.setStyle("-fx-font: 16 arial;");

        FileChooser fileChooser = new FileChooser();
        Button boutonFichier = new Button("Sélectionner une image");
        Label labelFichier = new Label("  Aucune image sélectionnée");

        File[] file = {null};
        boutonFichier.setOnMouseClicked(event -> {
            file[0] = fileChooser.showOpenDialog(this.stage);
            if (file[0] != null) {
                labelFichier.setText("  " + file[0].getName() + " sélectionnée");
            }
        });

        HBox image = new HBox(labelImage, boutonFichier, labelFichier);
       
        form.getChildren().add(image);

        //Bouton d'ajout
        Button boutonModifier = new Button("Modifier");
        boutonModifier.setStyle("-fx-alignment: top-right; -fx-end-margin: 10;");
        boutonModifier.setOnMouseClicked(event -> {
            TypeBien type = selectTypeBien.getValue() == null ? null : new TypeBien(selectTypeBien.getValue().toString());
            ClassificationBien classification = selectClassificationBien.getValue() == null ? null : new ClassificationBien(selectClassificationBien.getValue().toString());
            TypeChauffage chauffage = selectTypeChauffage.getValue() == null ? null : new TypeChauffage(selectTypeChauffage.getValue().toString());
            TypeEauChaude eau = selectEauChaude.getValue() == null ? null : new TypeEauChaude(selectEauChaude.getValue().toString());
            // Mise à jour de l'objet bien
            bien.getAdresse().setNoDansLaRue(fieldNoRue.getText());
            bien.getAdresse().setNomRue(fieldRue.getText());
            bien.getAdresse().setVille(fieldVille.getText());
            bien.setNoLogement(Integer.parseInt(fieldNoLogement.getText()));
            bien.setEtage(Integer.parseInt(fieldEtage.getText()));
            bien.setSurface(Integer.parseInt(fieldSurface.getText()));
            bien.setNbPieces(Integer.parseInt(fieldNbPieces.getText()));
            bien.setMeuble(checkboxMeuble.isSelected());

            bien.setTypeBien(type);
            bien.setClassificationBien(classification);
            bien.setTypeChauffage(chauffage);
            bien.setTypeEauChaude(eau);
            bien.setDescription(fieldDescription.getText());
            bien.setImage(file[0].getName());

            // Mise à jour dans la base de données
            JpaDaoAdresse jpaAdresse = new JpaDaoAdresse();
            Adresse adressebdd = jpaAdresse.find(bien.getAdresse().getId());
            if (adressebdd != null) {
                adressebdd.setNoDansLaRue(bien.getAdresse().getNoDansLaRue());
                adressebdd.setNomRue(bien.getAdresse().getNomRue());
                adressebdd.setVille(bien.getAdresse().getVille());
                jpaAdresse.update(adressebdd);
            }

            JpaDaoBien jpaBien = new JpaDaoBien();
            jpaBien.update(bien);
            try {
                new VueDetailsBien(stage, bien, jpaBien);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        });

        HBox hboxModifier = ButtonsUtil.createStyleButton(boutonModifier);
        VBox container = new VBox(boutonGoBack, titre, form, hboxModifier);
        this.scene = new Scene(container);

        this.stage.setScene(this.scene);
    }


    public Scene getScene() {
        return scene;
    }
}
