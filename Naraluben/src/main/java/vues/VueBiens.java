package vues;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jpaDao.JpaDaoBien;
import metier.Bien;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class VueBiens {
    private static int NB_BIENS_SUR_UNE_LIGNE = 3;
    private Stage stage;
    private Scene scene;

    public VueBiens(Stage stage) throws FileNotFoundException {

        this.stage = stage;

        //Titre de la vue
        Label titre = new Label("Liste des biens");
        titre.setStyle("-fx-font: 24 arial;-fx-text-fill: #5693bd;");
        titre.setLayoutX(30);
        titre.setLayoutY(30);

        //Récupération des biens
        JpaDaoBien jpa = new JpaDaoBien();
        List<Bien> biens = jpa.findAll();

        //Container pour lister les biens
        VBox containerBiens = new VBox();
        containerBiens.setLayoutX(80);
        containerBiens.setLayoutY(120);
        containerBiens.setSpacing(30);

        //Container pour une ligne de 4 biens
        HBox ligneBiens = new HBox();
        int i = 0;

        for (Bien bien : biens) {
            if (i == 0) {
                //Nouvelle ligne de 4 biens
                ligneBiens = new HBox();
                ligneBiens.setSpacing(30);
                containerBiens.getChildren().add(ligneBiens);
            }

            //Container pour un bien
            VBox containerBien = new VBox();

            Label labelId = new Label("bien : " + bien.getId());
            Label labelSurface = new Label("surface : " + bien.getSurface() + " m²");
            Label labelType = new Label("type : " + bien.getTypeBien().getTypeBien());

            Image imageBien = new Image(new FileInputStream("src/main/images/" + bien.getImage()));
            ImageView imageView = new ImageView(imageBien);
            imageView.setFitHeight(280);
            imageView.setFitWidth(400);

            //On redirige vers la vue DétailsBien au click sur un bien
            containerBien.setOnMouseClicked(event -> new VueDetailsBien(this.stage));

            containerBien.getChildren().addAll(imageView, labelId, labelSurface, labelType);
            ligneBiens.getChildren().add(containerBien);

            if (i == NB_BIENS_SUR_UNE_LIGNE) i = 0;
            else i++;
        }

        //Bouton ajout d'un bien
        Button buttonNouveauBien = new Button("Nouveau bien");
        buttonNouveauBien.setLayoutX(200);
        buttonNouveauBien.setLayoutY(30);

        //On redirige vers la vue VuejoutBien au click
        buttonNouveauBien.setOnAction(event -> new VueAjoutBien(this.stage));

        //On ajoute les différents éléments à la page
        Group page = new Group(titre, buttonNouveauBien, containerBiens);

        //On affiche la vue Bien
        ScrollPane sp = new ScrollPane();
        sp.setContent(page);
        this.scene = new Scene(sp);
        stage.setScene(this.scene);
    }

    public Scene getScene() {
        return scene;
    }
}
