package jpaDao;

import dao.DaoProprietaire;
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

    public List<Proprietaire> findAll() {
        return super.findAll(Proprietaire.class);
    }

}
