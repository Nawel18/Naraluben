package vues;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
        VBox containerBien = new VBox();
        Button b = new Button("Details Bien");
        //Donné du bien
        Label labelTitle1 = new Label("Information du Bien : ");
        Region spacer, spacer1, spacer2;
        spacer = new Region();
        spacer1 = new Region();
        spacer2 = new Region();
        spacer.setMinHeight(10);
        spacer1.setMinHeight(10);
        spacer2.setMinHeight(10);
        labelTitle1.setStyle("-fx-font-size: 18px;");
        Label labelTailleSurface = new Label("Surface totale : " + bien.getSurface());
        Label labelNbPiece = new Label("Nombre de pièce : " + bien.getNbPieces());
        Label labelSituation = new Label("Situation : " + bien.getSituation());
        Label labelDescription = new Label("Description : ");
        labelDescription.setStyle("-fx-font-size: 18px;");
        Label labelNomDescription = new Label(bien.getDescription());

        //image
        Image imageBien = new Image(new FileInputStream("src/main/images/" + bien.getImage()));
        ImageView imageView = new ImageView(imageBien);
        imageView.setFitHeight(1000);
        imageView.setFitWidth(1000);
        //Placement image
        HBox hbox = new HBox();
        Region leftRegion = new Region();
        Region rightRegion = new Region();
        HBox.setHgrow(leftRegion, Priority.ALWAYS);
        HBox.setHgrow(rightRegion, Priority.ALWAYS);
        hbox.getChildren().addAll(leftRegion, imageView, rightRegion);
        //On garde le ratio pour les dimensions
        imageView.setPreserveRatio(true);
        //Ajout des blocs
        containerBien.getChildren().addAll(hbox, labelTitle1, spacer, labelTailleSurface, labelNbPiece, labelSituation, spacer1, labelDescription, spacer2, labelNomDescription);
        //Placement du containeur
        containerBien.setPadding(new Insets(10, 10, 10, 10));

        // couleur arriere plan
        containerBien.setBackground(Background.fill(Color.web("#FFFFFF")));

        Group page = new Group(containerBien);
        //création de la scène
        ScrollPane scroll = new ScrollPane();
        scroll.setContent(page);
        //création de la scène
        this.scene = new Scene(scroll);
        stage.setScene(this.scene);
        stage.show();

    }
}
