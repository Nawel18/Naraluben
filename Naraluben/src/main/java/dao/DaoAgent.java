package dao;

import metier.Agent;

public interface DaoAgent {
    public Agent findByNoTiers(int noTiers);
}
