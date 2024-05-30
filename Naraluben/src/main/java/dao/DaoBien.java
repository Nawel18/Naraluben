package dao;

import metier.Bien;

public interface DaoBien extends Dao<Bien> {

    public Bien find(int id);
}
