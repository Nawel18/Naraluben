package jpaDao;

import dao.DaoTiers;
import jakarta.persistence.NoResultException;
import metier.Tiers;

import java.util.List;

public class JpaDaoTiers extends JpaDao<Tiers> implements DaoTiers {

    public JpaDaoTiers() {
        super();
    }

    public List<Tiers> findAll() {
        return super.findAll(Tiers.class);
    }

    public Tiers findByEmailAndPassword(String email, String password) {
        Tiers tiers = null;

        try {
            tiers = (Tiers) entityManager.createQuery("SELECT o FROM Tiers o WHERE o.email = :email AND o.password = :password", Tiers.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            // Handle no result case
        }

        return tiers;
    }

}
