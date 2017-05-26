package resourceClasses;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.InputStream;
import java.util.Properties;

@Startup
@Singleton
public class ConfigHelper {
	//Atributes
	private Properties mongo;
	private Properties mysql;
	private Properties app;


	//Methods
	public ConfigHelper() {
		try {
			// cargar configuracion de mongo
			mongo = new Properties();
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("mongo.properties");
			mongo.load(inputStream);

			// cargar configuracion de mysql
			mysql = new Properties();
			inputStream = getClass().getClassLoader().getResourceAsStream("mysql.properties");
			mysql.load(inputStream);

			// cargar configuracion de politweets
			app = new Properties();
			inputStream = getClass().getClassLoader().getResourceAsStream("app.properties");
			app.load(inputStream);
		}catch (Exception ex){
			ex.printStackTrace();
		}
	}

	public Properties getMongo() {return mongo;}
	public Properties getMysql() {return mysql;}
	public Properties getApp() {return app;}

	public String mongoGet(String property){return mongo.getProperty(property);}
	public String mysqlGet(String property){return mysql.getProperty(property);}
	public String appGet(String property){return app.getProperty(property);}
}