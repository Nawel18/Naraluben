import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import vues.VueConnexion;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {

        /*
        JpaDaoAdresse jpa = new JpaDaoAdresse();
        Adresse adresse = jpa.find(1);

        System.out.println("Adresse : " + adresse.getId());
         */

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        // Définir la taille du Stage en fonction des dimensions de l'écran
        stage.setX(screenBounds.getMinX());
        stage.setY(screenBounds.getMinY());
        stage.setWidth(screenBounds.getWidth());
        stage.setHeight(screenBounds.getHeight());

        // Page d'accueil -> Connexio

        VueConnexion connexion = new VueConnexion(stage);
        stage.setTitle("Naraluben");
        stage.show();
    }

}
