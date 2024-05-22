package jpaDao;

import dao.Dao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public abstract class JpaDao<T> implements Dao<T> {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;
    private EntityTransaction transaction;

    public JpaDao() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
        this.transaction = entityManager.getTransaction();
    }

    public boolean create(T obj) {
        this.transaction.begin();
        entityManager.persist(obj); //On dit qu'on veut le linker avec la bdd
        this.transaction.commit(); //On insert en bdd ce nouveau type tiers
        this.close();
        return true;
    }

    public T find(Class c, int id) {
        T obj = (T) entityManager.find(c, id);
        return obj;
    }

    public List<T> findAll(Class c) {
        List<T> objs = entityManager.createQuery("SELECT o FROM " + c.getName() + " o").getResultList();
        return objs;
    }

    public boolean update(T obj) {
        this.transaction.begin();
        entityManager.merge(obj);
        this.transaction.commit();
        this.close();
        return true;
    }

    public boolean delete(T obj) {
        this.transaction.begin();
        entityManager.remove(obj);
        this.transaction.commit(); //On delete en bdd
        this.close();
        return true;
    }

    public boolean deleteAll(Class c) {
        this.transaction.begin();
        entityManager.createQuery("DELETE FROM " + c.getName()).executeUpdate();
        this.transaction.commit();
        return true;
    }

    public void close() /* qui permet de fermer la connexion à la base de données ou le fichier. */ {
        if (this.transaction.isActive()) {
            this.transaction.rollback();
        }
        entityManager.close();
        entityManagerFactory.close();
    }
}
