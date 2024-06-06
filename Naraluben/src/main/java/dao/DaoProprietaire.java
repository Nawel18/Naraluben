package dao;

import metier.Proprietaire;

public interface DaoProprietaire {
    public Proprietaire find(int id);

    public Proprietaire findByNoTiers(int noTiers);
}
