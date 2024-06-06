package vues;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import jpaDao.JpaDaoAdresse;
import jpaDao.JpaDaoBien;
import jpaDao.JpaDaoBienProprietaire;
import jpaDao.JpaDaoProprietaire;
import metier.*;
import utils.ButtonsUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class VueModifierBien {
    private Stage stage;
    private Scene scene;

    public VueModifierBien(Stage stage, Bien bien, Tiers tiersConnecte) {
        this.stage = stage;

        //Titre de la vue
        Label titre = new Label("Modification du bien");
        titre.setStyle("-fx-font: 30 arial;-fx-text-fill: #5693bd;-fx-padding: 30px;");

        Button boutonGoBack = ButtonsUtil.createGoBackButton(this.stage, tiersConnecte);
        HBox hboxGoback = ButtonsUtil.createStyleButton(boutonGoBack, "vert");

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
        String noLogementText = bien.getNoLogement() == null ? "" : String.valueOf(bien.getNoLogement());
        fieldNoLogement.setText(noLogementText);
        HBox noLogement = new HBox(labelNoLogement, fieldNoLogement);
        form.getChildren().add(noLogement);

        //Etage
        Label labelEtage = new Label("Etage : ");
        labelEtage.setStyle("-fx-font: 16 arial;");

        TextField fieldEtage = new TextField();
        fieldEtage.setMinWidth(300);
        String etageText = bien.getEtage() == null ? "" : String.valueOf(bien.getEtage());
        fieldEtage.setText(etageText);

        HBox etage = new HBox(labelEtage, fieldEtage);
        form.getChildren().add(etage);

        //surface
        Label labelSurface = new Label("Surface (en m²) : ");
        labelSurface.setStyle("-fx-font: 16 arial;");

        TextField fieldSurface = new TextField();
        fieldSurface.setMinWidth(300);
        String surfaceText = bien.getSurface() == null ? "" : String.valueOf(bien.getSurface());
        fieldSurface.setText(surfaceText);

        HBox surface = new HBox(labelSurface, fieldSurface);
        form.getChildren().add(surface);

        //nb pieces
        Label labelNbPieces = new Label("Nombre de pièces : ");
        labelNbPieces.setStyle("-fx-font: 16 arial;");

        TextField fieldNbPieces = new TextField();
        fieldNbPieces.setMinWidth(300);
        String nbPiecesText = bien.getNbPieces() == null ? "" : String.valueOf(bien.getNbPieces());
        fieldNbPieces.setText(nbPiecesText);

        HBox nbPieces = new HBox(labelNbPieces, fieldNbPieces);
        form.getChildren().add(nbPieces);

        // Création d'un DatePicker avec une date
        Label labeldate = new Label("Date : ");
        labeldate.setStyle("-fx-font: 16 arial;");

        DatePicker datePicker = new DatePicker(bien.getDateCreation());
        HBox fielddate = new HBox(labeldate, datePicker);
        form.getChildren().add(fielddate);

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
        if (bien.getTypeChauffage() != null) selectTypeChauffage.setValue(bien.getTypeChauffage().getTypeChauffage());

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
        Label labelFichier = new Label("  " + bien.getImage() + " sélectionnée");

        File[] file = {null};
        boutonFichier.setOnMouseClicked(event -> {
            file[0] = fileChooser.showOpenDialog(this.stage);
            if (file[0] != null) {
                labelFichier.setText("  " + file[0].getName() + " sélectionnée");
            } else {
                labelFichier.setText("  Aucune image sélectionnée");
            }
        });

        HBox image = new HBox(labelImage, boutonFichier, labelFichier);
        form.getChildren().add(image);

        //proprietaire
        //test si l'utilisateur connecté est un proprietaire
        JpaDaoProprietaire jpaProprietaire = new JpaDaoProprietaire();
        Proprietaire proprietaireConnecte = jpaProprietaire.findByNoTiers(tiersConnecte.getId());

        ChoiceBox selectProprietaire = new ChoiceBox();
        BienProprietaire dernierProprietaire;

        if (proprietaireConnecte == null) {

            Label labelProprietaire = new Label("Propriétaire : ");
            labelProprietaire.setStyle("-fx-font: 16 arial;");
            //Récupération des propriétaires
            //JpaDaoProprietaire jpaProprietaire = new JpaDaoProprietaire();
            List<Proprietaire> proprietaires = jpaProprietaire.findAll();

            selectProprietaire.setMinWidth(300);
            for (Proprietaire proprietaire : proprietaires) {
                selectProprietaire.getItems().add(proprietaire.getId() + "- " + proprietaire.getNoTiers().getPrenom() + " " + proprietaire.getNoTiers().getNom());
            }
            if (bien.getBienProprietaires().stream().count() > 0) {
                //Récupère le dernier propriétaire en date
                dernierProprietaire = getLastProprietaire(bien.getBienProprietaires());
                selectProprietaire.setValue(dernierProprietaire.getProprietaire().getId() + "- " + dernierProprietaire.getProprietaire().getNoTiers().getNom() + " " + dernierProprietaire.getProprietaire().getNoTiers().getPrenom());
            } else {
                dernierProprietaire = null;
            }
            HBox proprietaire = new HBox(labelProprietaire, selectProprietaire);
            form.getChildren().add(proprietaire);
        } else {
            dernierProprietaire = null;
        }

        //loué
        Label labelLoue = new Label("Logement loué : ");
        labelLoue.setStyle("-fx-font: 16 arial;");

        CheckBox checkboxLoue = new CheckBox();
        if (bien.getSituation() == 1) checkboxLoue.setSelected(true);

        HBox loue = new HBox(labelLoue, checkboxLoue);
        form.getChildren().add(loue);

        //Bouton d'ajout
        Button boutonModifier = new Button("Modifier");
        boutonModifier.setStyle("-fx-alignment: top-right; -fx-end-margin: 10;");

        boutonModifier.setOnMouseClicked(event -> {
            if (fieldNoRue.getText().isEmpty() || fieldRue.getText().isEmpty() || fieldVille.getText().isEmpty()) {
                Popup popup = new Popup();
                Label labelError = new Label("Merci de compléter l'adresse et de choisir une image");
                labelError.setStyle(" -fx-background-color: #de6767;-fx-padding: 10");
                popup.getContent().add(labelError);
                popup.show(this.stage);

            } else {
                TypeBien type = selectTypeBien.getValue() == null ? null : new TypeBien(selectTypeBien.getValue().toString());
                ClassificationBien classification = selectClassificationBien.getValue() == null ? null : new ClassificationBien(selectClassificationBien.getValue().toString());
                TypeChauffage chauffage = selectTypeChauffage.getValue() == null ? null : new TypeChauffage(selectTypeChauffage.getValue().toString());
                TypeEauChaude eau = selectEauChaude.getValue() == null ? null : new TypeEauChaude(selectEauChaude.getValue().toString());

                Integer surfaceValue = fieldSurface.getText().isEmpty() ? null : Integer.valueOf(fieldSurface.getText());
                Integer noLogementValue = fieldNoLogement.getText().isEmpty() ? null : Integer.valueOf(fieldNoLogement.getText());
                Integer etageValue = fieldEtage.getText().isEmpty() ? 0 : Integer.valueOf(fieldEtage.getText());
                Integer nbPiecesValue = fieldNbPieces.getText().isEmpty() ? null : Integer.valueOf(fieldNbPieces.getText());
                Integer loueValue = checkboxLoue.isSelected() ? 1 : 0;

                // Mise à jour de l'objet bien
                bien.getAdresse().setNoDansLaRue(fieldNoRue.getText());
                bien.getAdresse().setNomRue(fieldRue.getText());
                bien.getAdresse().setVille(fieldVille.getText());
                bien.setNoLogement(noLogementValue);
                bien.setEtage(etageValue);
                bien.setSurface(surfaceValue);
                bien.setNbPieces(nbPiecesValue);
                bien.setMeuble(checkboxMeuble.isSelected());
                bien.setDateCreation(datePicker.getValue());
                bien.setTypeBien(type);
                bien.setClassificationBien(classification);
                bien.setTypeChauffage(chauffage);
                bien.setTypeEauChaude(eau);
                bien.setDescription(fieldDescription.getText());
                bien.setSituation(loueValue);
                if (file[0] == null) {
                    bien.setImage(bien.getImage());
                } else {
                    bien.setImage(file[0].getName());
                    file[0] = ButtonsUtil.copyImage(file[0]);
                }

                // Mise à jour dans la base de données
                JpaDaoAdresse jpaAdresse = new JpaDaoAdresse();
                Adresse adressebdd = jpaAdresse.find(bien.getAdresse().getId());
                if (adressebdd != null) {
                    adressebdd.setNoDansLaRue(bien.getAdresse().getNoDansLaRue());
                    adressebdd.setNomRue(bien.getAdresse().getNomRue());
                    adressebdd.setVille(bien.getAdresse().getVille());
                    jpaAdresse.update(adressebdd);
                }

                // Mise à jour du bien en bdd
                JpaDaoBien jpaBien = new JpaDaoBien();
                jpaBien.update(bien);

                // Mise à jour éventuelle du bien_proprietaire en bdd
                if (selectProprietaire.getValue() != null) {
                    if (dernierProprietaire == null || (dernierProprietaire != null && Integer.parseInt(selectProprietaire.getValue().toString().split("-")[0]) != dernierProprietaire.getProprietaire().getId())) {

                        //Le propriétaire a changé
                        if (dernierProprietaire != null && Integer.parseInt(selectProprietaire.getValue().toString().split("-")[0]) != dernierProprietaire.getProprietaire().getId()) {
                            dernierProprietaire.setDateFin(LocalDate.now());
                            JpaDaoBienProprietaire jpaBienProprietaire = new JpaDaoBienProprietaire();
                            jpaBienProprietaire.update(dernierProprietaire);
                        }

                        JpaDaoProprietaire jpaProprietaire2 = new JpaDaoProprietaire();
                        Proprietaire nouveauProprietaire = jpaProprietaire2.find(Integer.parseInt(selectProprietaire.getValue().toString().split("-")[0]));
                        BienProprietaireId bienProprietaireId = new BienProprietaireId(bien, nouveauProprietaire);
                        BienProprietaire bienProprietaire = new BienProprietaire();
                        bienProprietaire.setId(bienProprietaireId);
                        bienProprietaire.setDateDebut(LocalDate.now());
                        JpaDaoBienProprietaire jpaBienProprietaire = new JpaDaoBienProprietaire();
                        jpaBienProprietaire.create(bienProprietaire);
                    }
                }

                try {
                    new VueDetailsBien(stage, bien, tiersConnecte);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        HBox hboxModifier = ButtonsUtil.createStyleButton(boutonModifier, "vert");

        VBox container = new VBox(hboxGoback, titre, form, hboxModifier);
        ScrollPane scroll = new ScrollPane();
        scroll.setContent(container);
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);
        this.scene = new Scene(scroll);

        this.stage.setScene(this.scene);
    }

    private static BienProprietaire getLastProprietaire(final Collection c) {
        final Iterator itr = c.iterator();
        Object lastElement = null;
        while (itr.hasNext()) {
            lastElement = itr.next();

            BienProprietaire bienProprietaire = (BienProprietaire) lastElement;
            if (bienProprietaire.getDateFin() == null) {
                return (BienProprietaire) lastElement;
            }
        }
        return (BienProprietaire) lastElement;
    }

    public Scene getScene() {
        return scene;
    }
}
