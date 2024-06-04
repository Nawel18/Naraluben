package jpaDao;

import dao.DaoTiers;
import metier.Tiers;

import java.util.List;

public class JpaDaoTiers extends JpaDao<Tiers> implements DaoTiers {

    public JpaDaoTiers() {
        super();
    }

    public List<Tiers> findAll() {
        return super.findAll(Tiers.class);
    }

}
