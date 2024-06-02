package utils;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import jpaDao.JpaDaoBien;
import metier.Bien;
import vues.VueBiens;
import vues.VueModifierBien;

import java.io.*;

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

    public static Button createModifierBouttonButton(Stage stage, Bien bien, JpaDaoBien jpaBien) {
        Button boutonGoBack = new Button("Modifier");
        boutonGoBack.setOnMouseClicked(event -> new VueModifierBien(stage, bien, jpaBien));
        return boutonGoBack;
    }

    public static HBox createStyleButton(Button button) {
        HBox hbox = new HBox();
        button.setStyle("-fx-background-color: #4CAF50; \n" +
                "    -fx-background-radius: 15; \n" +
                "    -fx-text-fill: white; \n" +
                "    -fx-font-size: 14px; \n" +
                "    -fx-padding: 10 20 10 20;\n" +
                "    -fx-border-color: #3e8e41; \n" +
                "    -fx-border-width: 2px; \n" +
                "    -fx-border-radius: 15; ");
        hbox.setStyle("-fx-alignment: center-right; -fx-padding: 15;");
        hbox.getChildren().add(button);
        return hbox;
    }

    public static File copyImage(File image) {
        if (image != null) {
            try (
                    InputStream in = new BufferedInputStream(
                            new FileInputStream(image.getAbsolutePath()));
                    OutputStream out = new BufferedOutputStream(
                            new FileOutputStream("src/main/images/" + image.getName()))) {

                byte[] buffer = new byte[1024];
                int lengthRead;
                while ((lengthRead = in.read(buffer)) > 0) {
                    out.write(buffer, 0, lengthRead);
                    out.flush();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return image;
    }
}
