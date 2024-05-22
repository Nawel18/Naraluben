import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import jpaDao.JpaDaoAdresse;
import metier.Adresse;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {

        JpaDaoAdresse jpa = new JpaDaoAdresse();
        Adresse adresse = jpa.find(1);

        System.out.println("Adresse : " + adresse.getId());

        Label l = new Label("Adress found : " + adresse.getId());
        Scene scene = new Scene(new StackPane(l), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

}

/*import jpaDao.JpaDaoAdresse;
import metier.Adresse;

public class Main {
    public static void main(String[] args) {
        //BDD 2

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        //création d'une adresse
        try {
            transaction.begin();
            Adresse adresse = new Adresse();
            adresse.setNoDansLaRue("19");
            adresse.setNomRue("Faux Puits");
            entityManager.persist(adresse);
            transaction.commit();
            System.out.println("Une adresse est crée " +adresse.getId());
        }catch (Exception e){
            System.out.println("Exception : "+ e.getMessage());
        }finally {
            if(transaction.isActive()){
                transaction.rollback();
            }
        }
        //rechercher un  type tier
        entityManager.close();
        entityManagerFactory.close();
    }
}
*/

