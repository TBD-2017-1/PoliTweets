package service;

import facade.KeywordFacade;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import facade.PoliticoFacade;
import model.Conglomerado;
import model.Keyword;
import model.Partido;
import model.Politico;

@Path("/politicos")
public class PoliticoService {
	
    @EJB 
    PoliticoFacade politicoFacadeEJB;
    @EJB
    KeywordFacade keywordFacadeEJB;
	
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
	
    @GET
    @Path("{id}/keywords")
    @Produces({"application/xml", "application/json"})
    public List<Keyword> getKeywords(@PathParam("id") Integer id) {
        return politicoFacadeEJB.find(id).getKeywords();
    }
	
    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Politico entity) {
        politicoFacadeEJB.create(entity);
    }
	
    @POST
    @Path("{id}/addkeyword")
    @Consumes({"application/xml", "application/json"})
    public void addKeyword(@PathParam("id") Integer id, Keyword keyword) {
        //Creacion nueva keyword
        keywordFacadeEJB.create(keyword);
        keyword = keywordFacadeEJB.findByValue(keyword);
        //Join
        Politico politico = politicoFacadeEJB.find(id);
        politico.addKeyword(keyword);
        keyword.addPolitico(politico);
        //Merge a BD
        politicoFacadeEJB.edit(politico);
        keywordFacadeEJB.edit(keyword);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Politico entity) {
    	entity.setId(id.intValue());
        politicoFacadeEJB.edit(entity);
    }
    
    @PUT
    @Path("{id}/setconglomerado")
    @Consumes({"application/xml", "application/json"})
    public void editConglomerado(@PathParam("id") Integer id, Conglomerado conglomerado) {
        politicoFacadeEJB.find(id).setConglomerado(conglomerado);
    }
    
    @PUT
    @Path("{id}/setpartido")
    @Consumes({"application/xml", "application/json"})
    public void editPartido(@PathParam("id") Integer id, Partido partido) {
        politicoFacadeEJB.find(id).setPartido(partido);
    }
    
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        politicoFacadeEJB.remove(politicoFacadeEJB.find(id));
    }
	
}
