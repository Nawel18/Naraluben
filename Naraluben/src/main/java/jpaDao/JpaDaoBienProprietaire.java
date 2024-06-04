package jpaDao;

import dao.DaoBienProprietaire;
import metier.BienProprietaire;

import java.util.List;

public class JpaDaoBienProprietaire extends JpaDao<BienProprietaire> implements DaoBienProprietaire {

    public JpaDaoBienProprietaire() {
        super();
    }

    public List<BienProprietaire> findAll() {
        return super.findAll(BienProprietaire.class);
    }

}
