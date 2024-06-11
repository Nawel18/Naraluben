package jpaDao;

import dao.DaoAdresse;
import metier.Adresse;

import java.util.List;

public class JpaDaoAdresse extends JpaDao<Adresse> implements DaoAdresse {

    public JpaDaoAdresse() {
        super();
    }

    @Override
    public Adresse find(int id) {
        return super.find(Adresse.class, id);
    }

    /*
    public Adresse findByNoAndRueAndVille(String noDansLaRue, String rue, String ville) {
        Adresse adresse = (Adresse) entityManager.createQuery(
                        "SELECT a FROM " + Adresse.class.getName() + " a WHERE a.no_dans_la_rue = :noDansLaRue AND a.nom_rue = :rue AND a.ville = :ville"
                )
                .setParameter("noDansLaRue", noDansLaRue)
                .setParameter("rue", rue)
                .setParameter("ville", ville)
                .getSingleResult();
        return adresse;
    }
    */


    public List<Adresse> findAll() {
        return super.findAll(Adresse.class);
    }
}
