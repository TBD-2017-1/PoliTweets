package resourceClasses;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

@Singleton
public class DaemonClass {
    @Context
    ServletContext context;

    @Inject
    ConfigHelper config;


    Logger logger = Logger.getLogger(getClass().getName());

    //Atributes
    ProcessBuilder pb;
    Process proc = null;
    String path;

    //Methods

    @PostConstruct
    private void init(){
        File classPath = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
        File packagePath = new File(classPath.getParent());
        File classesPath = new File(packagePath.getParent());
        String webInfPath = classesPath.getParent();

        path = webInfPath+"/lib/"+config.appGet("collector");

        logger.info("Collector: "+path);
        pb = new ProcessBuilder("java","-jar",path);
    }

    public void start(){
        try {
            stop();
            pb = new ProcessBuilder("java","-jar",path);
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

    public String toggle(){
        if(proc!=null) {
            proc.destroy();
            proc = null;
            return "off";
        }else{
            start();
            return getStatus();
        }
    }

    public String getStatus(){
        return (proc!=null)?"on":"off";
    }


}