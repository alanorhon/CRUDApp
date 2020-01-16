package utils;

import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
    private static Properties properties = new Properties();

    static {
        try {
            properties.load(Thread.currentThread().getContextClassLoader().getResource("config.properties")
                    .openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String propertyName) {
        return properties.getProperty(propertyName);
    }
}
