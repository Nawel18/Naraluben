package jpaDao;

import dao.DaoBienProprietaire;
import metier.Bien;
import metier.BienProprietaire;
import metier.Proprietaire;

import java.util.ArrayList;
import java.util.List;

public class JpaDaoBienProprietaire extends JpaDao<BienProprietaire> implements DaoBienProprietaire {

    public JpaDaoBienProprietaire() {
        super();
    }

    public List<BienProprietaire> findAll() {
        return super.findAll(BienProprietaire.class);
    }

    public List<Bien> findAllBiensByProprietaire(Proprietaire proprietaire) {

        List<BienProprietaire> bienProprietaires = entityManager.createQuery("SELECT o FROM BienProprietaire o WHERE o.proprietaire = :proprietaire AND o.dateFin IS null ", BienProprietaire.class)
                .setParameter("proprietaire", proprietaire)
                .getResultList();

        List<Bien> biens = new ArrayList<>();
        for (BienProprietaire bienProprietaire : bienProprietaires) {
            biens.add(bienProprietaire.getBien());
        }

        return biens;
    }

}
