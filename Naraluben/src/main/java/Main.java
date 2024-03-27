import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import metier.Adresse;

public class Main {
    public static void main(String[] args) {
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
