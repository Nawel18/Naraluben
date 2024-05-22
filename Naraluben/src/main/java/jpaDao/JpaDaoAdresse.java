package jpaDao;

import dao.DaoAdresse;
import metier.Adresse;
import metier.Bien;

import java.util.List;

public class JpaDaoAdresse extends JpaDao<Adresse> implements DaoAdresse {

    public JpaDaoAdresse() {
        super();
    }

    @Override
    public int nombreBiens(Adresse adresse) {
        return this.findAll().toArray().length;
    }

    @Override
    public List<Bien> BiensACetteAdresse(Adresse adresse) {
        return null;
    }

    @Override
    public boolean create(Adresse obj) {
        return super.create(obj);
    }

    @Override
    public Adresse find(int id) {
        return super.find(Adresse.class, id);
    }

    public List<Adresse> findAll() {
        return super.findAll(Adresse.class);
    }

    @Override
    public boolean update(Adresse obj) {
        return super.update(obj);
    }

    @Override
    public boolean delete(Adresse obj) {
        return super.delete(obj);
    }

    public boolean deleteAll() {
        return super.deleteAll(Adresse.class);
    }
}
