package jpaDao;

import dao.DaoBien;
import metier.Bien;

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

}
