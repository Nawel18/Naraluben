package jpaDao;

import dao.DaoBien;
import metier.Bien;
import metier.BienProprietaire;

import java.util.List;

public class JpaDaoBien extends JpaDao<Bien> implements DaoBien {

    public JpaDaoBien() {
        super();
    }

    @Override
    public Bien find(int id) {
        return super.find(Bien.class, id);
    }

    public List<Bien> findAll() {
        return super.findAll(Bien.class);
    }

    public boolean delete(Bien bien) {
        super.transaction.begin();
        for (BienProprietaire bienProprietaire : bien.getBienProprietaires()) {
            super.entityManager.remove(bienProprietaire);
        }
        super.entityManager.remove(bien);
        super.transaction.commit(); //On delete en bdd
        super.close();
        return true;
    }

}
