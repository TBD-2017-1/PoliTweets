package service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import facade.MetricaFacade;
import model.ConglomeradoMetrica;
import model.Metrica;
import model.PartidoMetrica;
import model.PoliticoMetrica;

@Path("/metricas")
public class MetricaService {
	
    @EJB 
    MetricaFacade metricaFacadeEJB;
	
    Logger logger = Logger.getLogger(MetricaService.class.getName());
	
    @GET
    @Produces({"application/xml", "application/json"})
    public List<Metrica> findAll(){
    	return metricaFacadeEJB.findAll();
    }
	
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Metrica find(@PathParam("id") Integer id) {
        return metricaFacadeEJB.find(id);
    }
    
    @GET
    @Path("{id}/conglomerados")
    @Produces({"application/xml", "application/json"})
    public List<ConglomeradoMetrica> getMetricaConglomerados(@PathParam("id") Integer id) {
        return metricaFacadeEJB.find(id).getConglomeradoMetrica();
    }
    
    @GET
    @Path("{id}/partidos")
    @Produces({"application/xml", "application/json"})
    public List<PartidoMetrica> getMetricaPartidos(@PathParam("id") Integer id) {
        return metricaFacadeEJB.find(id).getPartidoMetrica();
    }
    
    @GET
    @Path("{id}/politicos")
    @Produces({"application/xml", "application/json"})
    public List<PoliticoMetrica> getMetricaPoliticos(@PathParam("id") Integer id) {
        return metricaFacadeEJB.find(id).getPoliticoMetrica();
    }
	
    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Metrica entity) {
        metricaFacadeEJB.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Metrica entity) {
    	entity.setId(id.intValue());
        metricaFacadeEJB.edit(entity);
    }
	
    @DELETE
    @Path("{id}")
    public void remove(Metrica entity) {
        metricaFacadeEJB.remove(entity);
    }
}
