package vues;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class VueConnexion {
    private Scene scene;

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

        TextField fieldPassword = new TextField();
        fieldPassword.setMinWidth(300);

        HBox password = new HBox(labelPassword, fieldPassword);
        password.setAlignment(Pos.CENTER);
        container.getChildren().add(password);

        Button connexion = new Button("Connexion");
        container.getChildren().add(connexion);
        connexion.setOnMouseClicked(event -> {
            //faire la condition de connexion
            try {
                new VueBiens(stage);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
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
