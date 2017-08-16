package Properties;

import java.util.Properties;

/**
 * Created by Lennard on 16-8-2017.
 */
public class TwitterPropertiesManager {
    private static final String PROPERTIES_PATH = "build/resources/main/twitter.properties";
    private PropertiesManager propertiesManager = new PropertiesManager();
    private Properties properties;

    public TwitterPropertiesManager()
    {
        properties = propertiesManager.getProperties(PROPERTIES_PATH);
    }

    public String getConsumerKey()
    {
        return properties.getProperty("CONSUMER_KEY");
    }

    public String getConsumerSecret()
    {
        return properties.getProperty("CONSUMER_SECRET");
    }

    public String getToken()
    {
        return properties.getProperty("TOKEN");
    }

    public String getTokenSecret()
    {
        return properties.getProperty("TOKEN_SECRET");
    }
}
