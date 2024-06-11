package dao;

import metier.Bien;
import metier.Proprietaire;

import java.util.List;

public interface DaoBienProprietaire {
    public List<Bien> findAllBiensByProprietaire(Proprietaire proprietaire);
}
