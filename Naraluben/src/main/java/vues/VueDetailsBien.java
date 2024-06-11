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
import jpaDao.JpaDaoAgent;
import jpaDao.JpaDaoBien;
import metier.Agent;
import metier.Bien;
import metier.BienProprietaire;
import metier.Tiers;
import utils.ButtonsUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Iterator;

public class VueDetailsBien extends ScrollPane {

    private Scene scene;

    public VueDetailsBien(Stage stage, Bien bien, Tiers tiersConnecte) throws FileNotFoundException {
        //Récupération des biens
        JpaDaoBien jpa = new JpaDaoBien();
        bien = jpa.find(bien.getId());
        // Création des conteneurs VBox
        VBox containerTitre = createVBox();
        VBox containerImage = createVBox();
        VBox containerAdresse = createVBox();

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
        imageView.setFitWidth(500);  // Largeur fixe
        imageView.setFitHeight(500); // Hauteur fixe
        imageView.setPreserveRatio(true);
        containerImage.getChildren().add(imageView);

        // Utilisation pour créer le bouton de retour
        Button boutonGoBack = ButtonsUtil.createGoBackButton(stage, tiersConnecte);
        HBox hboxGoback = ButtonsUtil.createStyleButton(boutonGoBack, "vert");

        // Test si l'utilisateur connecté est un agent
        // Utilisation pour créer le bouton de suppression
        JpaDaoAgent jpaAgent = new JpaDaoAgent();
        Agent agent = jpaAgent.findByNoTiers(tiersConnecte.getId());

        HBox hboxSupprimer = new HBox();
        if (agent != null) {
            Button boutonSupprimer = ButtonsUtil.createDeleteButton(jpa, bien, stage, tiersConnecte);
            hboxSupprimer.getChildren().add(ButtonsUtil.createStyleButton(boutonSupprimer, "rouge"));
            hboxSupprimer.setStyle("-fx-alignment: center-left; -fx-padding: 15;");
        }

        // Utilisation pour créer le bouton de retour
        Button boutonModifierBien = ButtonsUtil.createModifierBouttonButton(stage, bien, tiersConnecte);
        HBox hboxModifier = ButtonsUtil.createStyleButton(boutonModifierBien, "vert");

        containerGoback.getChildren().add(boutonGoBack);
        StackPane.setAlignment(containerGoback, Pos.TOP_RIGHT);
        StackPane.setMargin(containerGoback, new Insets(10));
        StackPane stackPane = new StackPane(containerGoback);
        HBox containerSupprimer = new HBox();
        containerSupprimer.getChildren().addAll(hboxGoback, hboxSupprimer, hboxModifier);
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
        containerBien.getChildren().add(labelSituation);

        //Données du bien
        if (bien.getBienProprietaires().stream().count() > 0) {
            //Récupère le dernier propriétaire en date
            BienProprietaire dernierProprietaire = getLastProprietaire(bien.getBienProprietaires());
            Label labelProprietaire = createStyledLabel("Propriétaire : " + dernierProprietaire.getProprietaire().getNoTiers().getNom() + " " + dernierProprietaire.getProprietaire().getNoTiers().getPrenom(), "-fx-font: 16 arial;");
            containerBien.getChildren().add(labelProprietaire);
        }

        Label labelTitle1 = createStyledLabel("Information du Bien : ", "-fx-font-size: 28px;");
        containerBien.getChildren().add(labelTitle1);
        Label labelType;
        if (bien.getClassificationBien() == null) {
            labelType = createStyledLabel("Type : " + bien.getTypeBien().getTypeBien(), "-fx-font: 16 arial;");

        } else {
            labelType = createStyledLabel("Type : " + bien.getTypeBien().getTypeBien() + " (" + bien.getClassificationBien().getClassificationBien() + ")", "-fx-font: 16 arial;");
        }
        containerBien.getChildren().add(labelType);
        if (bien.getSurface() != null) {
            Label labelTailleSurface = createStyledLabel("Surface totale : " + bien.getSurface() + "m²", "-fx-font: 16 arial;");
            containerBien.getChildren().add(labelTailleSurface);
        }
        if (bien.getNbPieces() != null) {
            Label labelNbPiece = createStyledLabel("Nombre de pièce : " + bien.getNbPieces() + " pièces", "-fx-font: 16 arial;");
            containerBien.getChildren().add(labelNbPiece);
        }
        if (bien.getNbPieces() != null) {
            Label labelNbEtage = createStyledLabel("Nombre d'étage : " + bien.getEtage() + " étages", "-fx-font: 16 arial;");
            containerBien.getChildren().add(labelNbEtage);
        }

        if (bien.getDateCreation() != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formattedDate = bien.getDateCreation().format(formatter);
            Label labelDate = createStyledLabel("date : " + formattedDate, "-fx-font: 16 arial;");
            containerBien.getChildren().add(labelDate);

        }
        //Chauffage
        Label labelTypeChaufage;
        if (bien.getTypeChauffage() != null) {
            labelTypeChaufage = createStyledLabel("Type de chauffage : " + bien.getTypeChauffage().getTypeChauffage(), "-fx-font: 16 arial;");
            containerBien.getChildren().add(labelTypeChaufage);
        }
        //Eau chaude
        Label labelEauChaude;
        if (bien.getTypeEauChaude() != null) {
            labelEauChaude = createStyledLabel("Type eau chaude : " + bien.getTypeEauChaude().getTypeEauChaude(), "-fx-font: 16 arial;");
            containerBien.getChildren().add(labelEauChaude);
        }
        if (bien.getDescription() != null) {
            Label labelDescription = createStyledLabel("Description : ", "-fx-font-size: 28px;");
            containerBien.getChildren().add(labelDescription);
            Label labelNomDescription = createStyledLabel(bien.getDescription(), "-fx-font: 16 arial;");
            containerBien.getChildren().add(labelNomDescription);
        }
        //logement meublé ou non
        Label labelMeuble;
        if (bien.getMeuble()) {
            labelMeuble = createStyledLabel("Le logement est meublé", "-fx-font: 16 arial;");
            containerBien.getChildren().add(labelMeuble);
        } else {
            labelMeuble = createStyledLabel("Le logement n'est pas meublé", "-fx-font: 16 arial;");
            containerBien.getChildren().add(labelMeuble);
        }

        return containerBien;
    }

    public static Label createStyledLabel(String text, String style) {
        Label label = new Label(text);
        label.setStyle(style);
        return label;
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

}
