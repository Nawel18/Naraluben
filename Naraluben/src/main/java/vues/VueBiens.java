package vues;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class VueBiens {
    private Stage stage;
    private Scene scene;

    public VueBiens(Stage stage) {
        this.stage = stage;

        Button b = new Button("Nouveau bien");

        b.setLayoutX(200);
        b.setLayoutY(200);
        b.setStyle("-fx-text-fill: #006400;");

        Group ajoutBien = new Group(b);
        this.scene = new Scene(ajoutBien, 1700, 950, true);

        b.setOnAction(event -> new VueAjoutBien(this.stage));

        stage.setScene(this.scene);
        stage.show();
    }

    public Scene getScene() {
        return scene;
    }
}
