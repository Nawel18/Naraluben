package vues;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import metier.Bien;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class VueDetailsBien extends ScrollPane {

    private Stage stage;
    private Scene scene;

    public VueDetailsBien(Stage stage, Bien bien) throws FileNotFoundException {
        this.stage = stage;
        //Container pour un bien
        VBox containerTitre = new VBox();
        VBox containerBien = new VBox();
        VBox containerImage = new VBox();
        VBox containerAdresse = new VBox();
        VBox container = new VBox();
        // espace
        Region spacer, spacer1, spacer2, spacer3, spacer4;
        spacer = new Region();
        spacer1 = new Region();
        spacer2 = new Region();
        spacer3 = new Region();
        spacer4 = new Region();
        spacer3.setMinHeight(10);
        spacer4.setMinHeight(10);
        spacer.setMinHeight(10);
        spacer1.setMinHeight(10);
        spacer2.setMinHeight(10);
        //Titre
        Label Titre = new Label("Détail");
        containerTitre.setMaxWidth(Double.MAX_VALUE); // Pour permettre au titre de s'étendre horizontalement
        containerTitre.setAlignment(Pos.CENTER);
        containerImage.setAlignment(Pos.CENTER);
        containerImage.setMaxWidth(Double.MAX_VALUE); // Pour permettre à l'image de s'étendre horizontalement
        Titre.setStyle("-fx-font-size: 48px; -fx-text-alignment: center;");
        containerTitre.getChildren().add(Titre);
        // verfication si le bien est disponible
        Label labelSituation;
        if (bien.getSituation() == 1) {
            labelSituation = new Label("Logement Non disponible pour le moment !!!!");
            labelSituation.setStyle("-fx-font-size: 48px; -fx-text-fill: red");
        } else {
            labelSituation = new Label("Logement disponible");
            labelSituation.setStyle("-fx-font-size: 18px; -fx-color-label-visible: red");
        }
        //Donné du bien
        Label labelTitle1 = new Label("Information du Bien : ");
        labelTitle1.setStyle("-fx-font-size: 18px;");
        Label labelTailleSurface = new Label("Surface totale : " + bien.getSurface() + "m²");
        Label labelNbPiece = new Label("Nombre de pièce : " + bien.getNbPieces() + " pièces");
        Label labelDescription = new Label("Description : ");
        labelDescription.setStyle("-fx-font-size: 18px;");
        Label labelNomDescription = new Label(bien.getDescription());
        //Ajout des blocs
        containerBien.getChildren().addAll(labelSituation, labelTitle1, spacer, labelTailleSurface, labelNbPiece, spacer1, labelDescription, spacer2, labelNomDescription);
        //Adresse
        Label labelTitle1Adresse = new Label("Coordonnées géographiques : ");
        labelTitle1Adresse.setStyle("-fx-font-size: 18px;");
        Label labelNomRue = new Label("Adresse : " + bien.getAdresse().getNoDansLaRue() + " " + bien.getAdresse().getNomRue() + " " + bien.getAdresse().getVille());
        containerAdresse.getChildren().addAll(spacer3, labelTitle1Adresse, spacer4, labelNomRue);
        //image
        Image imageBien = new Image(new FileInputStream("src/main/images/" + bien.getImage()));
        ImageView imageView = new ImageView(imageBien);
        //On garde le ratio pour les dimensions
        imageView.setPreserveRatio(true);
        containerImage.getChildren().add(imageView);


        // padding
        containerBien.setPadding(new Insets(10, 10, 10, 10));
        containerAdresse.setPadding(new Insets(10, 10, 10, 10));
        container.getChildren().addAll(containerTitre, containerImage, containerBien, containerAdresse);
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
}
