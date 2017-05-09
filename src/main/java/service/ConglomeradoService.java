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

import facade.ConglomeradoFacade;
import model.Conglomerado;

@Path("/conglomerados")
public class ConglomeradoService {
	
	@EJB 
	ConglomeradoFacade conglomeradoFacadeEJB;
	
	Logger logger = Logger.getLogger(ConglomeradoService.class.getName());
	
	@GET
	@Produces({"application/xml", "application/json"})
	public List<Conglomerado> findAll(){
		return conglomeradoFacadeEJB.findAll();
	}
	
	@GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Conglomerado find(@PathParam("id") Integer id) {
        return conglomeradoFacadeEJB.find(id);
    }
	
	@POST
    @Consumes({"application/xml", "application/json"})
    public void create(Conglomerado entity) {
        conglomeradoFacadeEJB.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Conglomerado entity) {
    	entity.setId(id.intValue());
        conglomeradoFacadeEJB.edit(entity);
    }
	

}
