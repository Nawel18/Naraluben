package vues;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class VueDetailsBien {

    private Stage stage;
    private Scene scene;

    public VueDetailsBien(Stage stage) {
        this.stage = stage;

        Button b = new Button("Details Bien");

        Group ajoutBien = new Group(b);
        this.scene = new Scene(ajoutBien);

        stage.setScene(this.scene);
        stage.show();
    }
}
