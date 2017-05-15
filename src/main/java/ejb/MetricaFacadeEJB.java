package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.MetricaFacade;
import model.Metrica;

@Stateless
public class MetricaFacadeEJB extends AbstractFacade<Metrica> implements MetricaFacade {	
	
    @PersistenceContext(unitName = "politweetsPU")
    private EntityManager em;

    public MetricaFacadeEJB() {
        super(Metrica.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }
}
