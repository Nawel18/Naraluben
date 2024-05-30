package vues;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jpaDao.JpaDaoBien;
import metier.Bien;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class VueBiens {
    private Stage stage;
    private Scene scene;

    public VueBiens(Stage stage) throws FileNotFoundException {

        this.stage = stage;

        //Titre de la vue
        Label titre = new Label("Liste des biens");
        titre.setStyle("-fx-font: 24 arial;-fx-text-fill: #5693bd;");
        titre.setLayoutX(30);
        titre.setLayoutY(30);

        //Container pour les biens
        VBox containerBiens = new VBox();
        containerBiens.setSpacing(10);
        containerBiens.setLayoutX(200);
        containerBiens.setLayoutY(200);

        //Récupération des biens
        JpaDaoBien jpa = new JpaDaoBien();
        List<Bien> biens = jpa.findAll();

        for (Bien bien : biens) {
            //Container pour un bien
            VBox containerBien = new VBox();

            Label labelId = new Label("bien : " + bien.getId());
            Label labelSurface = new Label("surface : " + bien.getSurface() + " m²");
            Label labelType = new Label("type : " + bien.getTypeBien().getTypeBien());

            Image imageBien = new Image(new FileInputStream("src/main/images/" + bien.getImage()));
            ImageView imageView = new ImageView(imageBien);

            imageView.setFitHeight(455);
            imageView.setFitWidth(500);
            //On garde le ratio pour les dimensions
            imageView.setPreserveRatio(true);

            containerBien.getChildren().addAll(imageView, labelId, labelSurface, labelType);
            containerBiens.getChildren().add(containerBien);
        }

        //Bouton ajout d'un bien
        Button buttonNouveauBien = new Button("Nouveau bien");
        buttonNouveauBien.setLayoutX(700);
        buttonNouveauBien.setLayoutY(500);

        //On redirige vers la vue VuejoutBien au click
        buttonNouveauBien.setOnAction(event -> new VueAjoutBien(this.stage));

        //On ajoute les différents éléments à la page
        Group page = new Group(titre, buttonNouveauBien, containerBiens);

        //On affiche la vue Bien
        this.scene = new Scene(page);
        stage.setScene(this.scene);
    }

    public Scene getScene() {
        return scene;
    }
}
