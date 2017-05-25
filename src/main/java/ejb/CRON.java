package ejb;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;

import javax.ejb.Startup;
import javax.inject.Singleton;
import java.util.logging.Logger;

@Startup
@Singleton
public class CRON {
	Logger logger = Logger.getLogger(CRON.class.getName());

	@PostConstruct
	public void init(){
		logger.info("Buenos dias");
	}

	@Schedule(second = "*",persistent = false)
	public void doSmth(){
		logger.info("HOLA!");
	}
}
