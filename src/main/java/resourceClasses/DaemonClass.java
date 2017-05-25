package resourceClasses;

import java.io.IOException;

public class DaemonClass {
    //Atributes
    
    String route = "/home/Politweets/resources/collector-1.2.jar";
    Process proc;
    ProcessBuilder pb;
    
    //Methods
    public DaemonClass() {
        pb = new ProcessBuilder("java", "-jar", route);
    }
    
    public void start() throws IOException{
        this.stop();
        proc = pb.start();
    }
    
    public void stop(){
        if(proc != null){
            proc.destroy();
            proc = null;
        }
    }
}