package vues;

import javafx.beans.binding.Bindings;
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
        Region spacer, spacer1, spacer2, spacer3;
        spacer = new Region();
        spacer1 = new Region();
        spacer2 = new Region();
        spacer.setMinHeight(10);
        spacer1.setMinHeight(10);
        spacer2.setMinHeight(10);
        //Titre
        Label Titre = new Label("Détail");
        containerTitre.setAlignment(Pos.CENTER);
        containerImage.setAlignment(Pos.CENTER);
        Titre.setStyle("-fx-font-size: 48px; -fx-text-alignment: center;");
        containerTitre.getChildren().add(Titre);
        //Donné du bien
        Label labelTitle1 = new Label("Information du Bien : ");
        labelTitle1.setStyle("-fx-font-size: 18px;");
        Label labelTailleSurface = new Label("Surface totale : " + bien.getSurface() + "m²");
        Label labelNbPiece = new Label("Nombre de pièce : " + bien.getNbPieces() + " pièces");
        Label labelSituation = new Label("Situation : " + bien.getSituation());
        Label labelDescription = new Label("Description : ");
        labelDescription.setStyle("-fx-font-size: 18px;");
        Label labelNomDescription = new Label(bien.getDescription());
        //Adresse
        Label labelTitle1Adresse = new Label("Coordonnées géographiques : ");
        labelTitle1Adresse.setStyle("-fx-font-size: 18px;");
        Label labelNomRue = new Label("Adresse : " + bien.getAdresse().getNoDansLaRue() + " " + bien.getAdresse().getNomRue() + " " + bien.getAdresse().getVille());
        containerAdresse.getChildren().addAll(labelTitle1Adresse, labelNomRue);
        //image
        Image imageBien = new Image(new FileInputStream("src/main/images/" + bien.getImage()));
        ImageView imageView = new ImageView(imageBien);
        //On garde le ratio pour les dimensions
        imageView.setPreserveRatio(true);
        containerImage.getChildren().add(imageView);


        //Ajout des blocs
        containerBien.getChildren().addAll(labelTitle1, spacer, labelTailleSurface, labelNbPiece, labelSituation, spacer1, labelDescription, spacer2, labelNomDescription);
        //Placement du containeur
        double leftPadding = 10; // Padding à gauche
        double rightPadding = 900; // Padding à droite
        double topPadding = 10; // Padding en haut
        double bottomPadding = 10;
        containerBien.paddingProperty().bind(
                Bindings.createObjectBinding(() ->
                                new Insets(topPadding, rightPadding, bottomPadding, leftPadding),
                        stage.widthProperty() // Utilise la largeur de la fenêtre
                )
        );

        // couleur arriere plan
        containerBien.setBackground(Background.fill(Color.web("#FFFFFF")));
        container.getChildren().addAll(containerTitre, containerImage, containerBien, containerAdresse);
        //création de la scène
        ScrollPane scroll = new ScrollPane();
        scroll.setContent(container);
        //création de la scène
        this.scene = new Scene(scroll);
        stage.setScene(this.scene);

    }
}
