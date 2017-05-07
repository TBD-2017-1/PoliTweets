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

import facade.PartidoFacade;
import model.Partido;

@Path("/partido")
public class PartidoService {
	
	@EJB 
	PartidoFacade partidoFacadeEJB;
	
	Logger logger = Logger.getLogger(PartidoService.class.getName());
	
	@GET
	@Produces({"application/xml", "application/json"})
	public List<Partido> findAll(){
		return partidoFacadeEJB.findAll();
	}
	
	@GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Partido find(@PathParam("id") Integer id) {
        return partidoFacadeEJB.find(id);
    }
	
	@POST
    @Consumes({"application/xml", "application/json"})
    public void create(Partido entity) {
        partidoFacadeEJB.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Partido entity) {
    	entity.setId(id.intValue());
        partidoFacadeEJB.edit(entity);
    }
	

}
