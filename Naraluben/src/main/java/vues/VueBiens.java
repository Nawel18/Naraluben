package vues;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import jpaDao.JpaDaoAgent;
import jpaDao.JpaDaoBien;
import jpaDao.JpaDaoBienProprietaire;
import jpaDao.JpaDaoProprietaire;
import metier.Agent;
import metier.Bien;
import metier.Proprietaire;
import metier.Tiers;
import utils.ButtonsUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class VueBiens {

    private static int NB_BIENS_SUR_UNE_LIGNE = 2;
    private Stage stage;
    private Scene scene;

    public VueBiens(Stage stage, Tiers tiersConnecte) throws FileNotFoundException {

        this.stage = stage;

        //Titre de la vue
        Label titre = new Label("Liste des biens");
        titre.setStyle("-fx-font: 30 arial;-fx-text-fill: #5693bd;-fx-padding: 30px;");

        //Récupération des biens
        List<Bien> biens = listeBiens(tiersConnecte);

        //Container pour lister les biens
        VBox containerBiens = new VBox();
        containerBiens.setSpacing(30);
        containerBiens.setLayoutX(80);
        containerBiens.setLayoutY(100);

        //ajoute un 4ème bien sur la ligne suivant les dimensions de l'écran
        if (NB_BIENS_SUR_UNE_LIGNE == 2) {
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            if (screenBounds.getWidth() > 1900) NB_BIENS_SUR_UNE_LIGNE++;
        }

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
                    new VueDetailsBien(this.stage, bien, tiersConnecte);
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

        //test si l'utilisateur connecté est un agent
        JpaDaoAgent jpaAgent = new JpaDaoAgent();
        Agent agent = jpaAgent.findByNoTiers(tiersConnecte.getId());

        Boolean estAgent = agent == null ? false : true;

        if (estAgent) {
            //Bouton ajout d'un bien
            Image imagePlus = new Image(new FileInputStream("src/main/images/plus.png"));
            ImageView imageView = new ImageView(imagePlus);
            imageView.setFitHeight(200);
            imageView.setFitWidth(200);
            VBox boutonNouveauBien = new VBox(imageView);
            boutonNouveauBien.setStyle("-fx-padding: 45px;");
            ligneBiens.getChildren().add(boutonNouveauBien);
            //On redirige vers la vue VuejoutBien au click
            boutonNouveauBien.setOnMouseClicked(event -> new VueAjoutBien(this.stage, tiersConnecte));
        }

        //Déconnection
        Button boutonDeconnexion = new Button("Déconnexion");
        HBox hboxGoback = ButtonsUtil.createStyleButton(boutonDeconnexion, "vert");
        boutonDeconnexion.setOnMouseClicked(event -> new VueConnexion(stage));

        //Utilisateurs
        HBox hboxUtilisateur = new HBox();
        if (estAgent) {
            Button boutonUtilisateur = new Button("Utilisateurs");
            hboxUtilisateur.getChildren().add(ButtonsUtil.createStyleButton(boutonUtilisateur, "vert"));
            boutonUtilisateur.setOnMouseClicked(event -> new VueTiers(stage, tiersConnecte));
        }

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

    private List<Bien> listeBiens(Tiers tiers) {

        JpaDaoProprietaire jpaProprietaire = new JpaDaoProprietaire();
        Proprietaire proprietaire = jpaProprietaire.findByNoTiers(tiers.getId());

        if (proprietaire != null) {
            JpaDaoBienProprietaire jpaBienProprietaire = new JpaDaoBienProprietaire();
            return jpaBienProprietaire.findAllBiensByProprietaire(proprietaire);

        } else {
            JpaDaoAgent jpaAgent = new JpaDaoAgent();
            Agent agent = jpaAgent.findByNoTiers(tiers.getId());

            if (agent != null) {
                JpaDaoBien jpaBien = new JpaDaoBien();
                return jpaBien.findAll();
            }
        }
        return null;
    }
}
