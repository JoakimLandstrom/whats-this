package se.jola.whatsthis.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyReader {

    public static String readProperty(String propertyName) {

        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("src/main/resources/application.properties");

            prop.load(input);

            String property = prop.getProperty(propertyName);

            return property;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
