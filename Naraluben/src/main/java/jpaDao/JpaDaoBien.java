package jpaDao;

import dao.DaoBien;
import metier.Bien;

import java.util.List;

public class JpaDaoBien extends JpaDao<Bien> implements DaoBien {

    public JpaDaoBien() {
        super();
    }

    @Override
    public boolean create(Bien obj) {
        return super.create(obj);
    }

    @Override
    public Bien find(int id) {
        return super.find(Bien.class, id);
    }

    public List<Bien> findAll() {
        return super.findAll(Bien.class);
    }

    @Override
    public boolean update(Bien obj) {
        return super.update(obj);
    }

    @Override
    public boolean delete(Bien obj) {
        return super.delete(obj);
    }
}
