package dao;

import metier.Adresse;

public interface DaoAdresse extends Dao<Adresse> {

    public Adresse find(int id);

    //public Adresse findByNoAndRueAndVille(String noDansLaRue, String rue, String ville);
}
