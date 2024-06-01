package utils;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import jpaDao.JpaDaoBien;
import metier.Bien;
import vues.VueBiens;

import java.io.FileNotFoundException;

public class ButtonsUtil {
    // Méthode pour rediriger vers VueBiens
    public static void redirectVueBiens(Stage stage) {
        try {
            new VueBiens(stage);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Création du bouton de suppression
    public static Button createDeleteButton(JpaDaoBien jpa, Bien bien, Stage stage) {
        Button boutonSupprimer = new Button("Supprimer");
        boutonSupprimer.setOnMouseClicked(event -> {
            jpa.delete(bien);
            redirectVueBiens(stage);
        });
        return boutonSupprimer;
    }

    // Création du boutton de retour
    public static Button createGoBackButton(Stage stage) {
        Button boutonGoBack = new Button("Retour");
        boutonGoBack.setOnMouseClicked(event -> redirectVueBiens(stage));
        return boutonGoBack;
    }
}
