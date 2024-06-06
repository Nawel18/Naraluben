package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import jpaDao.JpaDaoBien;
import metier.Bien;
import metier.Tiers;
import vues.VueAjoutTiers;
import vues.VueBiens;
import vues.VueModifierBien;

import java.io.*;
import java.util.Objects;

public class ButtonsUtil {
    // Méthode pour rediriger vers VueBiens
    public static void redirectVueBiens(Stage stage, Tiers tiers) {
        try {
            new VueBiens(stage, tiers);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Création du bouton de suppression
    public static Button createDeleteButton(JpaDaoBien jpa, Bien bien, Stage stage, Tiers tiersConnecte) {
        Button boutonSupprimer = new Button("Supprimer");
        boutonSupprimer.setOnAction(e -> {
            // Afficher une boîte de dialogue de confirmation
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Voulez-vous vraiment Supprimer ce bien?");
            alert.setContentText("Cliquez sur OK pour confirmer.");

            // Attendre la réponse de l'utilisateur
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    // Code à exécuter si l'utilisateur confirme
                    //System.out.println("Action exécutée !");
                    jpa.delete(bien);
                    redirectVueBiens(stage, tiersConnecte);

                } else {
                    // Code à exécuter si l'utilisateur annule
                    //System.out.println("Action annulée !");
                }
            });
        });

        return boutonSupprimer;
    }

    // Création du boutton de retour
    public static Button createGoBackButton(Stage stage, Tiers tiersConnecte) {
        Button boutonGoBack = new Button("Retour");
        boutonGoBack.setOnMouseClicked(event -> redirectVueBiens(stage, tiersConnecte));
        return boutonGoBack;
    }

    public static Button createModifierBouttonButton(Stage stage, Bien bien, Tiers tiers) {
        Button boutonGoBack = new Button("Modifier");
        boutonGoBack.setOnMouseClicked(event -> new VueModifierBien(stage, bien, tiers));
        return boutonGoBack;
    }

    public static Button createNouveauTiersButton(Stage stage, String type, Tiers tiers) {
        Button boutonNouveauTiers = new Button("Nouveau tiers");
        if (type == "Agent") {
            boutonNouveauTiers.setText("Nouvel agent");
        }
        if (type == "Propriétaire") {
            boutonNouveauTiers.setText("Nouveau propriétaire");
        }
        boutonNouveauTiers.setOnMouseClicked(event -> new VueAjoutTiers(stage, type, tiers));
        return boutonNouveauTiers;
    }

    public static HBox createStyleButton(Button button, String couleur) {
        HBox hbox = new HBox();
        if (Objects.equals(couleur, "rouge")) {
            button.setStyle("-fx-background-color: #f44336; \n" +
                    "    -fx-background-radius: 15; \n" +
                    "    -fx-text-fill: white; \n" +
                    "    -fx-font-size: 14px; \n" +
                    "    -fx-padding: 10 20 10 20;\n" +
                    "    -fx-border-color: #b71c1c; \n" +
                    "    -fx-border-width: 2px; \n" +
                    "    -fx-border-radius: 15; ");
            hbox.setStyle("-fx-alignment: center-right; -fx-padding: 15;");

        } else if (Objects.equals(couleur, "vert")) {
            button.setStyle("-fx-background-color: #4CAF50; \n" +
                    "    -fx-background-radius: 15; \n" +
                    "    -fx-text-fill: white; \n" +
                    "    -fx-font-size: 14px; \n" +
                    "    -fx-padding: 10 20 10 20;\n" +
                    "    -fx-border-color: #3e8e41; \n" +
                    "    -fx-border-width: 2px; \n" +
                    "    -fx-border-radius: 15; ");
            hbox.setStyle("-fx-alignment: center-right; -fx-padding: 15;");

        }
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
