package vues;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VueAjoutBien {
    private Stage stage;
    private Scene scene;

    public VueAjoutBien(Stage stage) {
        this.stage = stage;

        //Titre de la vue
        Label titre = new Label("Nouveau bien");
        titre.setStyle("-fx-font: 30 arial;-fx-text-fill: #5693bd;-fx-padding: 30px;");

        //Container pour le formulaire
        VBox form = new VBox();
        form.setStyle("-fx-padding: 50px;");

        //surface
        Label labelSurface = new Label("Surface (en m²) : ");
        labelSurface.setStyle("-fx-font: 16 arial;");

        TextField fieldSurface = new TextField();
        fieldSurface.setMinWidth(300);

        HBox surface = new HBox(labelSurface, fieldSurface);

        Region spacer = new Region();
        spacer.setMinHeight(30);
        form.getChildren().addAll(surface, spacer);

        //type de bien
        Label labelTypeBien = new Label("Type de bien : ");
        labelTypeBien.setStyle("-fx-font: 16 arial;");

        ChoiceBox selectTypeBien = new ChoiceBox();
        selectTypeBien.setMinWidth(300);
        for (metier.enums.TypeBien type : metier.enums.TypeBien.values()) {
            selectTypeBien.getItems().add(type);
        }
        HBox typeBien = new HBox(labelTypeBien, selectTypeBien);
        form.getChildren().add(typeBien);

        Button boutonNouveauBien = new Button("Ajouter");

        VBox container = new VBox(titre, form, boutonNouveauBien);
        this.scene = new Scene(container);

        stage.setScene(this.scene);
    }

    public Scene getScene() {
        return scene;
    }
}
