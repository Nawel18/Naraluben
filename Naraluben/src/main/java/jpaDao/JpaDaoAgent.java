package jpaDao;

import dao.DaoAgent;
import metier.Agent;

import java.util.List;

public class JpaDaoAgent extends JpaDao<Agent> implements DaoAgent {

    public JpaDaoAgent() {
        super();
    }

    public List<Agent> findAll() {
        return super.findAll(Agent.class);
    }

}