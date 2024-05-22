package dao;

import metier.Adresse;
import metier.Bien;

import java.util.List;

public interface DaoAdresse extends Dao<Adresse> {
    public int nombreBiens(Adresse adresse);

    public List<Bien> BiensACetteAdresse(Adresse adresse);

    public Adresse find(int id);
}
