package logic.configuration;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by aefrd on 20.09.2016.
 */
public class ConfigurationManager {
    private static Properties config;
    // класс извлекает информацию из файла config.properties

    private ConfigurationManager() {
    }

    public static boolean initProperty(ServletConfig request) {
        try {
            InputStream inputStream = request.getServletContext().getResourceAsStream("/WEB-INF/classes/config.properties");
            config = new Properties();
            config.load(inputStream);
            LogConfiguration.LOGGER.debug("config resources loaded");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static String getProperty(String key) {
        return config.getProperty(key);
    }


}