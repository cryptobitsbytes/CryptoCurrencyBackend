package Properties;

import java.util.Properties;

/**
 * Created by Lennard on 19-8-2017.
 */
public class MongoDBPropertiesManager {
    private static final String PROPERTIES_PATH = "build/resources/main/mongodb.properties";
    private PropertiesManager propertiesManager = new PropertiesManager();
    private Properties properties;

    public MongoDBPropertiesManager()
    {
        properties = propertiesManager.getProperties(PROPERTIES_PATH);
    }

    public String getHostName()
    {
        return properties.getProperty("HOSTNAME");
    }

    public int getPort()
    {
        return Integer.valueOf(properties.getProperty("PORT"));
    }
}
