package service;

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

import facade.ConglomeradoFacade;
import model.Conglomerado;
import model.Keyword;
import model.Partido;
import model.Politico;

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
	
    @GET
    @Path("{id}/partidos")
    @Produces({"application/xml", "application/json"})
    public List<Partido> getPartidos(@PathParam("id") Integer id) {
        return conglomeradoFacadeEJB.find(id).getListaPartidos();
    }
	
    @GET
    @Path("{id}/politicos")
    @Produces({"application/xml", "application/json"})
    public List<Politico> getPoliticos(@PathParam("id") Integer id) {
        return conglomeradoFacadeEJB.find(id).getListaPoliticos();
    }
	
    @GET
    @Path("{id}/keywords")
    @Produces({"application/xml", "application/json"})
    public List<Keyword> getKeywords(@PathParam("id") Integer id) {
        return conglomeradoFacadeEJB.find(id).getKeywords();
    }
	
    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Conglomerado entity) {
        conglomeradoFacadeEJB.create(entity);
    }
	
    @POST
    @Path("{id}/addpartido")
    @Consumes({"application/xml", "application/json"})
    public void addPartido(@PathParam("id") Integer id, Partido partido) {
        conglomeradoFacadeEJB.find(id).addPartido(partido);
    }
	
    @POST
    @Path("{id}/addpolitico")
    @Consumes({"application/xml", "application/json"})
    public void addPolitico(@PathParam("id") Integer id, Politico politico) {
        conglomeradoFacadeEJB.find(id).addPolitico(politico);
    }
	
    @POST
    @Path("{id}/addkeyword")
    @Consumes({"application/xml", "application/json"})
    public void addKeyword(@PathParam("id") Integer id, Keyword keyword) {
        conglomeradoFacadeEJB.find(id).addKeyword(keyword);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Conglomerado entity) {
    	entity.setId(id.intValue());
        conglomeradoFacadeEJB.edit(entity);
    }
	
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        conglomeradoFacadeEJB.remove(conglomeradoFacadeEJB.find(id));
    }
}
