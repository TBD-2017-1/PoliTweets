package service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import facade.AdminFacade;
import jdk.nashorn.internal.runtime.JSONListAdapter;
import model.Admin;

@Path("/admins")
public class AdminService {
	
	@EJB 
	AdminFacade adminFacadeEJB;
	
	Logger logger = Logger.getLogger(AdminService.class.getName());
	
	@GET
	@Produces({"application/xml", "application/json"})
	public List<Admin> findAll(){
		return adminFacadeEJB.findAll();
	}
	
	@GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Admin find(@PathParam("id") Integer id) {
        return adminFacadeEJB.find(id);
    }
	
	@GET
	@Path("verify/{username}/{password}")
	@Produces({"application/xml", "application/json"})
	public Admin verify(@PathParam("username") String username, @PathParam("password") String password){
		List<Admin> admins = adminFacadeEJB.findAll();
		for (Admin admin : admins) {
			if(admin.getUsername().equals(username) && admin.getPassword().equals(password)){
				return admin;
			}
		}
		return new Admin();
	}
	
	@POST
    @Consumes({"application/xml", "application/json"})
    public void create(Admin entity) {
        adminFacadeEJB.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Admin entity) {
    	entity.setId(id.intValue());
        adminFacadeEJB.edit(entity);
    }
	

}
