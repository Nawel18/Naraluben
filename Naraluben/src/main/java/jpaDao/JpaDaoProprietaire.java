package jpaDao;

import dao.DaoProprietaire;
import jakarta.persistence.NoResultException;
import metier.Proprietaire;

import java.util.List;

public class JpaDaoProprietaire extends JpaDao<Proprietaire> implements DaoProprietaire {

    public JpaDaoProprietaire() {
        super();
    }

    @Override
    public Proprietaire find(int id) {
        return super.find(Proprietaire.class, id);
    }

    public Proprietaire findByNoTiers(int noTiers) {
        Proprietaire proprietaire = null;

        try {
            proprietaire = (Proprietaire) entityManager.createQuery("SELECT o FROM Proprietaire o WHERE o.noTiers.id = :noTiers", Proprietaire.class)
                    .setParameter("noTiers", noTiers)
                    .getSingleResult();
        } catch (NoResultException e) {
            // Handle no result case
        }

        return proprietaire;
    }

    public List<Proprietaire> findAll() {
        return super.findAll(Proprietaire.class);
    }

}
