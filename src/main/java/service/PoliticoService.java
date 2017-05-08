package service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import facade.PoliticoFacade;
import model.Politico;

@Path("/politico")
public class PoliticoService {
	
	@EJB 
	PoliticoFacade politicoFacadeEJB;
	
	Logger logger = Logger.getLogger(PoliticoService.class.getName());
	
	@GET
	@Produces({"application/xml", "application/json"})
	public List<Politico> findAll(){
		return politicoFacadeEJB.findAll();
	}
	
	@GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Politico find(@PathParam("id") Integer id) {
        return politicoFacadeEJB.find(id);
    }
	
	@POST
    @Consumes({"application/xml", "application/json"})
    public void create(Politico entity) {
        politicoFacadeEJB.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Politico entity) {
    	entity.setId(id.intValue());
        politicoFacadeEJB.edit(entity);
    }
	

}
