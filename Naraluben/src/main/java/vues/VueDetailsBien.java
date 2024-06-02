package vues;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jpaDao.JpaDaoBien;
import metier.Bien;
import utils.ButtonsUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class VueDetailsBien extends ScrollPane {

    private Scene scene;

    public VueDetailsBien(Stage stage, Bien bien, JpaDaoBien jpa) throws FileNotFoundException {
        // Création des conteneurs VBox
        VBox containerTitre = createVBox();
        VBox containerImage = createVBox();
        VBox containerAdresse = createVBox();
        VBox containerSupprimer = createVBox();
        VBox containerGoback = createVBox();
        VBox container = createVBox();

        //Titre
        Label Titre = new Label("Détail");
        containerTitre.setMaxWidth(Double.MAX_VALUE); // Pour permettre au titre de s'étendre horizontalement
        containerTitre.setAlignment(Pos.CENTER);
        containerImage.setAlignment(Pos.CENTER);
        containerImage.setMaxWidth(Double.MAX_VALUE); // Pour permettre à l'image de s'étendre horizontalement
        Titre.setStyle("-fx-font-size: 48px; -fx-text-alignment: center;");
        containerTitre.getChildren().add(Titre);
        //Adresse
        Label labelTitle1Adresse = createStyledLabel("Coordonnées géographiques : ", "-fx-font-size: 28px");
        Label labelNomRue = createStyledLabel("Adresse : " + bien.getAdresse().getNoDansLaRue() + " " + bien.getAdresse().getNomRue() + " " + bien.getAdresse().getVille(), "-fx-font-size: 16px");
        containerAdresse.getChildren().addAll(labelTitle1Adresse, labelNomRue);
        //image
        Image imageBien = new Image(new FileInputStream("src/main/images/" + bien.getImage()));
        ImageView imageView = new ImageView(imageBien);
        containerImage.getChildren().add(imageView);

        // Utilisation pour créer le bouton de suppression
        Button boutonSupprimer = ButtonsUtil.createDeleteButton(jpa, bien, stage);

        // Utilisation pour créer le bouton de retour
        Button boutonGoBack = ButtonsUtil.createGoBackButton(stage);
        // Utilisation pour créer le bouton de retour
        Button boutonModifierBien = ButtonsUtil.createModifierBouttonButton(stage, bien, jpa);
        HBox hboxModifier = ButtonsUtil.createStyleButton(boutonModifierBien);

        containerGoback.getChildren().add(boutonGoBack);
        StackPane.setAlignment(containerGoback, Pos.TOP_RIGHT);
        StackPane.setMargin(containerGoback, new Insets(10));
        StackPane stackPane = new StackPane(containerGoback);
        containerSupprimer.getChildren().addAll(boutonSupprimer, hboxModifier);
        VBox containerBien = informationBien(bien);

        // padding
        containerBien.setPadding(new Insets(10, 10, 10, 10));
        containerAdresse.setPadding(new Insets(10, 10, 10, 10));
        container.getChildren().addAll(stackPane, containerTitre, containerImage, containerBien, containerAdresse, containerSupprimer);
        container.setBackground(Background.fill(Color.web("#FFFFFF")));

        //création de la scène

        ScrollPane scroll = new ScrollPane();
        scroll.setContent(container);
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);
        //création de la scène
        this.scene = new Scene(scroll);
        stage.setScene(this.scene);

    }

    // Méthode pour créer une VBox
    private static VBox createVBox() {
        VBox vbox = new VBox();
        vbox.setSpacing(15);
        return vbox;
    }


    private static VBox informationBien(Bien bien) {
        VBox containerBien = createVBox();
        // verfication si le bien est disponible
        Label labelSituation;
        if (bien.getSituation() == 1) {
            labelSituation = new Label("Logement Non disponible pour le moment !!!!");
            labelSituation.setStyle("-fx-font-size: 48px; -fx-text-fill: red");
        } else {
            labelSituation = new Label("Logement disponible");
            labelSituation.setStyle("-fx-font-size: 18px; -fx-text-fill: red");
        }
        //Donné du bien
        Label labelTitle1 = createStyledLabel("Information du Bien : ", "-fx-font-size: 28px;");
        Label labelType;
        if (bien.getClassificationBien() == null) {
            labelType = createStyledLabel("Type : " + bien.getTypeBien().getTypeBien(), "-fx-font: 16 arial;");

        } else {
            labelType = createStyledLabel("Type : " + bien.getTypeBien().getTypeBien() + " (" + bien.getClassificationBien().getClassificationBien() + ")", "-fx-font: 16 arial;");
        }
        Label labelTailleSurface = createStyledLabel("Surface totale : " + bien.getSurface() + "m²", "-fx-font: 16 arial;");
        Label labelNbPiece = createStyledLabel("Nombre de pièce : " + bien.getNbPieces() + " pièces", "-fx-font: 16 arial;");
        Label labelNbEtage = createStyledLabel("Nombre d'étage : " + bien.getEtage() + " étages", "-fx-font: 16 arial;");
        Label labelDate = createStyledLabel("Date de fabrication : " + bien.getDateCreation(), "-fx-font: 16 arial;");
        Label labelclassification = createStyledLabel("Classification du bien : " + bien.getClassificationBien().getClassificationBien(), "-fx-font: 16 arial;");
        Label labelTypeChaufage = createStyledLabel("Type de chauffage : " + bien.getTypeChauffage().getTypeChauffage(), "-fx-font: 16 arial;");
        Label labelEauChaude = createStyledLabel("Type eau chaude : " + bien.getTypeEauChaude().getTypeEauChaude(), "-fx-font: 16 arial;");
        Label labelDescription = createStyledLabel("Description : ", "-fx-font-size: 28px;");
        Label labelNomDescription = createStyledLabel(bien.getDescription(), "-fx-font: 16 arial;");
        //logement meublé ou non
        Label labelMeuble;
        if (bien.getMeuble()) {
            labelMeuble = createStyledLabel("Le logement est meublé", "-fx-font: 16 arial;");
        } else {
            labelMeuble = createStyledLabel("Le logement n'est pas meublé", "-fx-font: 16 arial;");
        }
        //Ajout des blocs
        containerBien.getChildren().addAll(labelSituation, labelTitle1, labelType, labelTailleSurface, labelNbPiece, labelNbEtage, labelclassification, labelEauChaude, labelTypeChaufage, labelDate, labelMeuble, labelDescription, labelNomDescription);
        return containerBien;

    }

    public static Label createStyledLabel(String text, String style) {
        Label label = new Label(text);
        label.setStyle(style);
        return label;
    }

}
