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

    public List<Adresse> findAll() {
        return super.findAll(Adresse.class);
    }
}
