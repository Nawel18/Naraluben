import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;
import jpaDao.JpaDaoAdresse;
import metier.Adresse;
import vues.VueBiens;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {

        JpaDaoAdresse jpa = new JpaDaoAdresse();
        Adresse adresse = jpa.find(1);

        System.out.println("Adresse : " + adresse.getId());


        Button b1 = new Button("Main");

        /*
        Label l = new Label("Adress found : " + adresse.getId());
        TextField t = new TextField();
        ChoiceBox choiceBox = new ChoiceBox();


        t.setLayoutX(300);
        t.setLayoutY(300);

        choiceBox.getItems().add("Choice 1");
        choiceBox.getItems().add("Choice 2");
        choiceBox.getItems().add("Choice 3");
        HBox hbox = new HBox(choiceBox);

        //hbox.setLayoutX(400);
        //hbox.setLayoutY(400);

         */
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        // Définir la taille du Stage en fonction des dimensions de l'écran
        stage.setX(screenBounds.getMinX());
        stage.setY(screenBounds.getMinY());
        stage.setWidth(screenBounds.getWidth());
        stage.setHeight(screenBounds.getHeight());

        Group ajoutBien2 = new Group(b1);
        Scene scene2 = new Scene(ajoutBien2);

        VueBiens biens = new VueBiens(stage);

        b1.setOnAction(event -> new VueBiens(stage));

        stage.setScene(scene2);
        stage.show();
    }

}
