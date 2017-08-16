package Properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Lennard on 16-8-2017.
 */
public class PropertiesManager {

    protected Properties getProperties(String filePath)
    {

        Properties properties = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream(filePath);
            properties.load(input);
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException io) {
                    io.printStackTrace();
                }
            }

        }
        return properties;
    }
}
