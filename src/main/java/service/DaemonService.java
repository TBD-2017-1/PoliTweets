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
import javax.ws.rs.core.Response;

import facade.AdminFacade;
import model.Admin;

@Path("/daemon")
public class DaemonService {
	
    @GET
    @Path("{status}")
    @Produces({"application/xml", "application/json"})
    public Response setDaemonStatus(@PathParam("status") boolean status){
    	//To do code
        return Response.status(Response.Status.OK).build();
    }
	
}
