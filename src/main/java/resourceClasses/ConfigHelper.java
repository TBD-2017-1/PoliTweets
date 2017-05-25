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

	//Methods
	public ConfigHelper() {
		try {
			// cargar configuracion de mongo
			mongo = new Properties();
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("mongo.properties");
			mongo.load(inputStream);

			// cargar configuracion de mysql
			mysql = new Properties();
			inputStream = getClass().getClassLoader().getResourceAsStream("mongo.properties");
			mysql.load(inputStream);
		}catch (Exception ex){
			ex.printStackTrace();
		}
	}

	public Properties getMongo() {return mongo;}
	public Properties getMysql() {return mysql;}
	public String mongoGet(String property){return mongo.getProperty(property);}
	public String mysqlGet(String property){return mysql.getProperty(property);}
}