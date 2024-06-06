package jpaDao;

import dao.DaoAgent;
import jakarta.persistence.NoResultException;
import metier.Agent;

import java.util.List;

public class JpaDaoAgent extends JpaDao<Agent> implements DaoAgent {

    public JpaDaoAgent() {
        super();
    }

    public List<Agent> findAll() {
        return super.findAll(Agent.class);
    }

    public Agent findByNoTiers(int noTiers) {
        Agent agent = null;

        try {
            agent = (Agent) entityManager.createQuery("SELECT o FROM Agent o WHERE o.noTiers.id = :noTiers", Agent.class)
                    .setParameter("noTiers", noTiers)
                    .getSingleResult();
        } catch (NoResultException e) {
            // Handle no result case
        }

        return agent;
    }

}