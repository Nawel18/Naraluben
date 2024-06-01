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
import metier.*;
import utils.ButtonsUtil;

import java.io.*;

public class VueAjoutBien {
    private Stage stage;
    private Scene scene;

    public VueAjoutBien(Stage stage) {
        this.stage = stage;

        //Titre de la vue
        Label titre = new Label("Nouveau bien");
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
        fieldNoRue.setPromptText("N°");

        TextField fieldRue = new TextField();
        fieldRue.setMinWidth(300);
        fieldRue.setPromptText("Rue");

        TextField fieldVille = new TextField();
        fieldVille.setMinWidth(300);
        fieldVille.setPromptText("Ville");

        HBox adresse = new HBox(labelAdresse, fieldNoRue, fieldRue, fieldVille);
        form.getChildren().add(adresse);

        //No logement
        Label labelNoLogement = new Label("N° logement : ");
        labelNoLogement.setStyle("-fx-font: 16 arial;");

        TextField fieldNoLogement = new TextField();
        fieldNoLogement.setMinWidth(300);

        HBox noLogement = new HBox(labelNoLogement, fieldNoLogement);
        form.getChildren().add(noLogement);

        //Etage
        Label labelEtage = new Label("Etage : ");
        labelEtage.setStyle("-fx-font: 16 arial;");

        TextField fieldEtage = new TextField();
        fieldEtage.setMinWidth(300);

        HBox etage = new HBox(labelEtage, fieldEtage);
        form.getChildren().add(etage);

        //surface
        Label labelSurface = new Label("Surface (en m²) : ");
        labelSurface.setStyle("-fx-font: 16 arial;");

        TextField fieldSurface = new TextField();
        fieldSurface.setMinWidth(300);

        HBox surface = new HBox(labelSurface, fieldSurface);
        form.getChildren().add(surface);

        //nb pieces
        Label labelNbPieces = new Label("Nombre de pièces : ");
        labelNbPieces.setStyle("-fx-font: 16 arial;");

        TextField fieldNbPieces = new TextField();
        fieldNbPieces.setMinWidth(300);

        HBox nbPieces = new HBox(labelNbPieces, fieldNbPieces);
        form.getChildren().add(nbPieces);

        //meublé
        Label labelMeuble = new Label("Logement meublé : ");
        labelMeuble.setStyle("-fx-font: 16 arial;");

        CheckBox checkboxMeuble = new CheckBox();

        HBox meuble = new HBox(labelMeuble, checkboxMeuble);
        form.getChildren().add(meuble);

        //type de bien
        Label labelTypeBien = new Label("Type de bien : ");
        labelTypeBien.setStyle("-fx-font: 16 arial;");

        ChoiceBox selectTypeBien = new ChoiceBox();
        selectTypeBien.setMinWidth(300);
        selectTypeBien.getItems().addAll(metier.enums.TypeBien.values());
        HBox typeBien = new HBox(labelTypeBien, selectTypeBien);
        form.getChildren().add(typeBien);

        //classification du bien
        Label labelClassificationBien = new Label("Classification du bien : ");
        labelClassificationBien.setStyle("-fx-font: 16 arial;");

        ChoiceBox selectClassificationBien = new ChoiceBox();
        selectClassificationBien.setMinWidth(300);
        selectClassificationBien.getItems().addAll(metier.enums.ClassificationBien.values());

        HBox classificationBien = new HBox(labelClassificationBien, selectClassificationBien);
        form.getChildren().add(classificationBien);

        //type chauffage
        Label labelTypeChauffage = new Label("Type de chauffage : ");
        labelTypeChauffage.setStyle("-fx-font: 16 arial;");

        ChoiceBox selectTypeChauffage = new ChoiceBox();
        selectTypeChauffage.setMinWidth(300);
        selectTypeChauffage.getItems().addAll(metier.enums.TypeChauffage.values());

        HBox typeChauffage = new HBox(labelTypeChauffage, selectTypeChauffage);
        form.getChildren().add(typeChauffage);

        //type eau chaude
        Label labelEauChaude = new Label("Type eau chaude : ");
        labelEauChaude.setStyle("-fx-font: 16 arial;");

        ChoiceBox selectEauChaude = new ChoiceBox();
        selectEauChaude.setMinWidth(300);
        selectEauChaude.getItems().addAll(metier.enums.TypeEauChaude.values());

        HBox eauChaude = new HBox(labelEauChaude, selectEauChaude);
        form.getChildren().add(eauChaude);

        //description
        Label labelDescription = new Label("Description : ");
        labelDescription.setStyle("-fx-font: 16 arial;");

        TextArea fieldDescription = new TextArea();
        fieldDescription.setMinWidth(300);

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
        Button boutonNouveauBien = new Button("Ajouter");

        boutonNouveauBien.setOnMouseClicked(event -> {

            //Vérification que les champs obligatoires sont remplis
            if (fieldNoRue.getText().isEmpty() || fieldRue.getText().isEmpty() || fieldVille.getText().isEmpty() || file[0] == null) {
                Popup popup = new Popup();
                Label labelError = new Label("Merci de compléter l'adresse et de choisir une image");
                labelError.setStyle(" -fx-background-color: #de6767;-fx-padding: 10");
                popup.getContent().add(labelError);
                popup.show(this.stage);

            } else {
                //Mise à null en bdd des champs vides
                TypeBien type = selectTypeBien.getValue() == null ? null : new TypeBien(selectTypeBien.getValue().toString());
                ClassificationBien classification = selectClassificationBien.getValue() == null ? null : new ClassificationBien(selectClassificationBien.getValue().toString());
                TypeChauffage chauffage = selectTypeChauffage.getValue() == null ? null : new TypeChauffage(selectTypeChauffage.getValue().toString());
                TypeEauChaude eau = selectEauChaude.getValue() == null ? null : new TypeEauChaude(selectEauChaude.getValue().toString());

                Integer surfaceValue = fieldSurface.getText().isEmpty() ? null : Integer.valueOf(fieldSurface.getText());
                Integer noLogementValue = fieldNoLogement.getText().isEmpty() ? null : Integer.valueOf(fieldNoLogement.getText());
                Integer etageValue = fieldEtage.getText().isEmpty() ? null : Integer.valueOf(fieldEtage.getText());
                Integer nbPiecesValue = fieldNbPieces.getText().isEmpty() ? null : Integer.valueOf(fieldNbPieces.getText());

                Bien nouveauBien = new Bien(surfaceValue, noLogementValue, etageValue,
                        type, classification, chauffage, eau,
                        nbPiecesValue, checkboxMeuble.isSelected(), 1,
                        fieldDescription.getText(), file[0].getName());

                Adresse nouvelleAdresse = new Adresse(fieldNoRue.getText(), fieldRue.getText(), fieldVille.getText());

                ajouterBien(nouveauBien, nouvelleAdresse, file[0]);
                
                //Retour à la page liste biens
                try {
                    new VueBiens(this.stage);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        VBox container = new VBox(boutonGoBack, titre, form, boutonNouveauBien);
        this.scene = new Scene(container);

        this.stage.setScene(this.scene);
    }

    private void ajouterBien(Bien bien, Adresse adresse, File image) {

        //copie de l'image
        if (image != null) {
            try (
                    InputStream in = new BufferedInputStream(
                            new FileInputStream(image.getAbsolutePath()));
                    OutputStream out = new BufferedOutputStream(
                            new FileOutputStream("src/main/images/" + image.getName()))) {

                byte[] buffer = new byte[1024];
                int lengthRead;
                while ((lengthRead = in.read(buffer)) > 0) {
                    out.write(buffer, 0, lengthRead);
                    out.flush();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        /*
        //test si l'adresse existe déjà
        JpaDaoAdresse jpaAdresse = new JpaDaoAdresse();
        Adresse adresseTrouvee = jpaAdresse.findByNoAndRueAndVille(adresse.getNoDansLaRue(), adresse.getNomRue(), adresse.getVille());

        if (adresseTrouvee == null) {
            jpaAdresse.create(adresse);
            adresseTrouvee = jpaAdresse.findByNoAndRueAndVille(adresse.getNoDansLaRue(), adresse.getNomRue(), adresse.getVille());
        }
        */

        //test si l'adresse existe déjà
        JpaDaoAdresse jpaAdresse = new JpaDaoAdresse();
        jpaAdresse.create(adresse);

        bien.setAdresse(adresse);

        //création du bien en bdd
        JpaDaoBien jpaBien = new JpaDaoBien();
        jpaBien.create(bien);
    }

    public Scene getScene() {
        return scene;
    }
}
