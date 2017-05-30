package service;

import ejb.CronEJB;

import javax.ejb.EJB;
import javax.ws.rs.*;


@Path("/cron")
public class CronService {
    @EJB
    CronEJB cron;

    @POST
    @Path("index")
    @Consumes({"application/xml", "application/json"})
    public void doIndex(){
        cron.doIndex();
    }

    @POST
    @Path("metricas")
    @Consumes({"application/xml", "application/json"})
    public void doMetrica(){
        cron.doMetricas();
    }
	
}
