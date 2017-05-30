package service;

;
import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import classes.DaemonClass;

@Path("/daemon")
public class DaemonService {
    @Inject
    DaemonClass daemon;

    @GET
    @Path("toggle")
    @Produces({"application/xml", "application/json"})
    public Response toggleDaemon(){
        String response = Json.createObjectBuilder()
                .add("status",daemon.toggle())
                .build()
                .toString();
        return Response.status(Response.Status.OK).entity(response).build();
    }

    @GET
    @Path("status")
    @Produces({"application/xml", "application/json"})
    public Response getDaemonStatus(){
        String response = Json.createObjectBuilder()
                .add("status",daemon.getStatus())
                .build()
                .toString();

        return Response.status(Response.Status.OK).entity(response).build();
    }

    @GET
    @Path("{status}")
    @Produces({"application/xml", "application/json"})
    public Response setDaemonStatus(@PathParam("status") boolean status){
    	if(status){
    	    daemon.start();
        }else {
    	    daemon.stop();
        }
        return Response.status(Response.Status.OK).build();
    }
	
}
