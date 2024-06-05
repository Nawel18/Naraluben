package vues;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import jpaDao.JpaDaoAgent;
import jpaDao.JpaDaoProprietaire;
import jpaDao.JpaDaoTiers;
import metier.Agent;
import metier.Proprietaire;
import metier.Tiers;
import utils.ButtonsUtil;

import java.time.LocalDate;

public class VueAjoutTiers {

    private Stage stage;
    private Scene scene;

    public VueAjoutTiers(Stage stage, String type) {
        this.stage = stage;

        //Titre de la vue
        Label titre = new Label("Nouveau Tiers");
        titre.setStyle("-fx-font: 30 arial;-fx-text-fill: #5693bd;-fx-padding: 30px;");

        if (type == "Agent")
            titre.setText("Nouvel Agent");
        if (type == "Propriétaire")
            titre.setText("Nouveau Propriétaire");

        Button boutonGoBack = ButtonsUtil.createGoBackButton(this.stage);
        HBox hboxGoback = ButtonsUtil.createStyleButton(boutonGoBack, "vert");
        VBox containerGoback = new VBox();
        containerGoback.setSpacing(15);
        containerGoback.getChildren().add(boutonGoBack);
        StackPane.setAlignment(containerGoback, Pos.TOP_RIGHT);
        StackPane.setMargin(containerGoback, new Insets(10));
        StackPane stackPane = new StackPane(containerGoback);

        //Container pour le formulaire
        VBox form = new VBox();
        form.setStyle("-fx-padding: 50px;");
        form.setSpacing(15);

        //Nom
        Label labelNom = new Label("Nom : ");
        labelNom.setStyle("-fx-font: 16 arial;");

        TextField fieldNom = new TextField();
        fieldNom.setMinWidth(20);

        HBox nom = new HBox(labelNom, fieldNom);
        form.getChildren().add(nom);

        //Prénom
        Label labelPrénom = new Label("Prénom : ");
        labelPrénom.setStyle("-fx-font: 16 arial;");

        TextField fieldPrenom = new TextField();
        fieldPrenom.setMinWidth(300);

        HBox prenom = new HBox(labelPrénom, fieldPrenom);
        form.getChildren().add(prenom);

        //Date de naissance
        Label labelDate = new Label("Date de naissance : ");
        labelDate.setStyle("-fx-font: 16 arial;");

        DatePicker datePicker = new DatePicker(LocalDate.now());
        HBox fielddate = new HBox(labelDate, datePicker);
        form.getChildren().add(fielddate);

        //sécurité sociale
        Label labelNoSS = new Label("Numéro de sécurité sociale : ");
        labelNoSS.setStyle("-fx-font: 16 arial;");

        TextField fieldNoSS = new TextField();
        fieldNoSS.setMinWidth(300);

        HBox noSS = new HBox(labelNoSS, fieldNoSS);
        form.getChildren().add(noSS);

        //rib
        Label labelRib = new Label("RIB : ");
        labelRib.setStyle("-fx-font: 16 arial;");

        TextField fieldRib = new TextField();
        fieldRib.setMinWidth(300);

        HBox rib = new HBox(labelRib, fieldRib);
        form.getChildren().add(rib);

        //mot de passe
        Label labelMdp = new Label("Mot de passe : ");
        labelMdp.setStyle("-fx-font: 16 arial;");

        TextField fieldMdp = new TextField();
        fieldMdp.setMinWidth(300);

        HBox mdp = new HBox(labelMdp, fieldMdp);
        form.getChildren().add(mdp);

        //email
        Label labelEmail = new Label("Email : ");
        labelEmail.setStyle("-fx-font: 16 arial;");

        TextField fieldEmail = new TextField();
        fieldEmail.setMinWidth(300);

        HBox email = new HBox(labelEmail, fieldEmail);
        form.getChildren().add(email);

        //Bouton d'ajout
        Button boutonNouveauTiers = new Button("Ajouter");
        HBox hboxNouveauBien = ButtonsUtil.createStyleButton(boutonNouveauTiers, "vert");

        boutonNouveauTiers.setOnMouseClicked(event -> {

            //Vérification que les champs obligatoires sont remplis
            if (fieldNom.getText().isEmpty() || fieldPrenom.getText().isEmpty() || fieldMdp.getText().isEmpty()) {
                Popup popup = new Popup();
                Label labelError = new Label("Merci de compléter le nom/prénom et de rentrer un mot de passe");
                labelError.setStyle(" -fx-background-color: #de6767;-fx-padding: 10");
                popup.getContent().add(labelError);
                popup.show(this.stage);

            } else {
                //Mise à null en bdd des champs vides
                String nomValue = fieldNom.getText().isEmpty() ? null : fieldNom.getText();
                String prenomValue = fieldPrenom.getText().isEmpty() ? null : fieldPrenom.getText();
                String noSsValue = fieldNoSS.getText().isEmpty() ? null : fieldNoSS.getText();
                String ribValue = fieldRib.getText().isEmpty() ? null : fieldRib.getText();
                String mdpValue = fieldMdp.getText().isEmpty() ? null : fieldMdp.getText();
                String emailValue = fieldEmail.getText().isEmpty() ? null : fieldEmail.getText();

                Tiers nouveauTiers = new Tiers(nomValue, prenomValue, datePicker.getValue(), noSsValue, ribValue, mdpValue, emailValue);

                ajouterTier(nouveauTiers, type);
            }
        });

        VBox container = new VBox(stackPane, titre, form, hboxNouveauBien);
        ScrollPane scroll = new ScrollPane();
        scroll.setContent(container);
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);
        this.scene = new Scene(scroll);

        this.stage.setScene(this.scene);
    }

    public void ajouterTier(Tiers tiers, String type) {

        //création du tier en bdd
        JpaDaoTiers jpaTiers = new JpaDaoTiers();
        jpaTiers.create(tiers);

        if (type == "Agent") {
            //création de l'agent en bdd
            Agent agent = new Agent(tiers);
            JpaDaoAgent jpaAgent = new JpaDaoAgent();
            jpaAgent.create(agent);
        }
        if (type == "Propriétaire") {
            //création du propriétaire en bdd
            Proprietaire proprietaire = new Proprietaire(tiers);
            JpaDaoProprietaire jpaProprietaire = new JpaDaoProprietaire();
            jpaProprietaire.create(proprietaire);
        }

        new VueTiers(this.stage);
    }
}
