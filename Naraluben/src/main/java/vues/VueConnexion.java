package vues;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import jpaDao.JpaDaoTiers;
import metier.Tiers;

import java.io.FileNotFoundException;

public class VueConnexion {
    private Scene scene;

    private Popup popup = new Popup();

    public VueConnexion(Stage stage) {
        VBox container = createVBox();
        container.setAlignment(Pos.CENTER);
        Label labelTitre = new Label("Connexion");
        labelTitre.setStyle("-fx-font: 36 arial;");
        labelTitre.setAlignment(Pos.CENTER);
        container.getChildren().add(labelTitre);

        //Email
        Label labelEmail = new Label("Email : ");
        labelEmail.setStyle("-fx-font: 16 arial;");

        TextField fieldEmail = new TextField();
        fieldEmail.setMinWidth(300);

        HBox email = new HBox(labelEmail, fieldEmail);
        email.setAlignment(Pos.CENTER);
        container.getChildren().add(email);

        //Password
        Label labelPassword = new Label("Mot de passe : ");
        labelPassword.setStyle("-fx-font: 16 arial;");

        PasswordField fieldPassword = new PasswordField();
        fieldPassword.setMinWidth(300);

        HBox password = new HBox(labelPassword, fieldPassword);
        password.setAlignment(Pos.CENTER);
        container.getChildren().add(password);

        Button connexion = new Button("Connexion");
        container.getChildren().add(connexion);
        JpaDaoTiers tiers = new JpaDaoTiers();

        fieldPassword.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                Tiers tiersConnecte = tiers.findByEmailAndPassword(fieldEmail.getText(), fieldPassword.getText());

                if (tiersConnecte != null) {
                    //faire la condition de connexion
                    try {

                        popup.hide();

                        new VueBiens(stage, tiersConnecte);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                } else {

                    Label labelError = new Label("Mot de passe ou adresse mail incorrect");
                    labelError.setStyle(" -fx-background-color: #de6767;-fx-padding: 10");
                    popup.getContent().add(labelError);
                    if (!popup.isShowing()) {
                        popup.show(stage);
                    }
                }
            }
        });
        connexion.setOnMouseClicked(event -> {
            Tiers tiersConnecte = tiers.findByEmailAndPassword(fieldEmail.getText(), fieldPassword.getText());

            if (tiersConnecte != null) {
                //faire la condition de connexion
                try {
                    new VueBiens(stage, tiersConnecte);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            } else {

                Label labelError = new Label("Mot de passe ou adresse mail incorrect");
                labelError.setStyle(" -fx-background-color: #de6767;-fx-padding: 10");
                popup.getContent().add(labelError);
                popup.show(stage);
            }
        });

        this.scene = new Scene(container);
        stage.setScene(this.scene);
    }

    // Méthode pour créer une VBox
    private static VBox createVBox() {
        VBox vbox = new VBox(10);
        vbox.setSpacing(15);
        return vbox;
    }

}
