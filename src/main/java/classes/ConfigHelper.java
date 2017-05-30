package classes;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.InputStream;
import java.util.Properties;

@Startup
@Singleton
public class ConfigHelper {
	//Atributes
	private Properties app;


	//Methods
	@PostConstruct
	public void init(){
		try {
			// cargar configuracion de politweets
			app = new Properties();
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("app.properties");
			app.load(inputStream);
		}catch (Exception ex){
			ex.printStackTrace();
		}
	}

	public Properties getPropertiesObj(){return app;}
	public String mongoGet(String property){return app.getProperty("mongo."+property);}
	public String mysqlGet(String property){return app.getProperty("mysql."+property);}
	public String appGet(String property){return app.getProperty("app."+property);}
	public String get(String property){return app.getProperty(property);}
}