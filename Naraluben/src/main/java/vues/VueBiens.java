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
import utils.ButtonsUtil;

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
        titre.setStyle("-fx-font: 30 arial;-fx-text-fill: #5693bd;-fx-padding: 30px;");
        //Récupération des biens
        JpaDaoBien jpa = new JpaDaoBien();
        List<Bien> biens = jpa.findAll();
        //Container pour lister les biens
        VBox containerBiens = new VBox();
        containerBiens.setLayoutX(80);
        containerBiens.setLayoutY(100);
        containerBiens.setSpacing(30);
        //Container pour une ligne de 4 biens
        HBox ligneBiens = new HBox();
        ligneBiens.setSpacing(30);
        containerBiens.getChildren().add(ligneBiens);
        int i = 0;
        for (Bien bien : biens) {
            Label labelId = new Label("adresse : " + bien.getAdresse().getNoDansLaRue() + " " + bien.getAdresse().getNomRue() + ", " + bien.getAdresse().getVille());
            Label labelSurface = new Label("surface : " + bien.getSurface() + " m²");
            Label labelType = new Label("type : " + bien.getTypeBien().getTypeBien());
            Image imageBien = new Image(new FileInputStream("src/main/images/" + bien.getImage()));
            ImageView imageView = new ImageView(imageBien);
            imageView.setFitHeight(280);
            imageView.setFitWidth(400);
            //Container pour un bien
            VBox containerBien = new VBox(imageView, labelId, labelSurface, labelType);
            //On redirige vers la vue DétailsBien au click sur un bien
            containerBien.setOnMouseClicked(event -> {
                try {
                    new VueDetailsBien(this.stage, bien);
                    new VueDetailsBien(this.stage, bien);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
            ligneBiens.getChildren().add(containerBien);
            if (i == NB_BIENS_SUR_UNE_LIGNE) {
                //Nouvelle ligne de 4 biens
                ligneBiens = new HBox();
                ligneBiens.setSpacing(30);
                containerBiens.getChildren().add(ligneBiens);
                i = 0;
            } else i++;
        }
        //Bouton ajout d'un bien
        Image imagePlus = new Image(new FileInputStream("src/main/images/plus.png"));
        ImageView imageView = new ImageView(imagePlus);
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        VBox boutonNouveauBien = new VBox(imageView);
        boutonNouveauBien.setStyle("-fx-padding: 45px;");
        ligneBiens.getChildren().add(boutonNouveauBien);
        //On redirige vers la vue VuejoutBien au click
        boutonNouveauBien.setOnMouseClicked(event -> new VueAjoutBien(this.stage));


        //On redirige vers la vue VuejoutBien au click
        boutonNouveauBien.setOnMouseClicked(event -> new VueAjoutBien(this.stage));

        //Déconnection
        Button boutonDeconnexion = new Button("Déconnexion");
        HBox hboxGoback = ButtonsUtil.createStyleButton(boutonDeconnexion, "vert");
        boutonDeconnexion.setOnMouseClicked(event -> {
            new VueConnexion(stage);
        });

        //Utilisateurs
        Button boutonUtilisateur = new Button("Utilisateurs");
        HBox hboxUtilisateur = ButtonsUtil.createStyleButton(boutonUtilisateur, "vert");
        boutonUtilisateur.setOnMouseClicked(event -> {
            new VueTiers(stage);
        });

        HBox hautpage = new HBox(titre, hboxUtilisateur, hboxGoback);
        //On ajoute les différents éléments à la page
        Group page = new Group(hautpage, containerBiens);
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
