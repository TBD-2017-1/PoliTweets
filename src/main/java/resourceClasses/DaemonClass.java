package resourceClasses;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import java.io.IOException;

@Singleton
public class DaemonClass {
    @Context
    ServletContext context;

    @Inject
    ConfigHelper config;

    //Atributes
    ProcessBuilder pb;
    Process proc = null;

    //Methods
    public DaemonClass() {
        String path = context.getRealPath("/WEB-INF/lib/"+config.appGet("collector"));
        pb = new ProcessBuilder("java","-jar",path);
    }

    public void start(){
        try {
            stop();
            proc = pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        if(proc!=null) {
            proc.destroy();
            proc = null;
        }
    }


}