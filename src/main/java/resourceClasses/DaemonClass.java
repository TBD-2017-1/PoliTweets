package resourceClasses;

import javax.ejb.Singleton;
import javax.inject.Inject;
import java.io.IOException;

@Singleton
public class DaemonClass {
    @Inject
    ConfigHelper config;

    //Atributes
    ProcessBuilder pb;
    Process proc = null;

    //Methods
    public DaemonClass() {
        pb = new ProcessBuilder("java","-jar",config.appGet("collectorPath"));
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