package logic.configuration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by aefrd on 20.09.2016.
 */
public class ConfigurationManager {
    private static Properties config;
    public static Properties mailProperties;
    // ����� ��������� ���������� �� ����� config.properties

    private ConfigurationManager() {
    }

    public static boolean initProperty(ServletContext request) {
        try {
            InputStream inputStream = request.getResourceAsStream("/WEB-INF/classes/config.properties");
            config = new Properties();
            config.load(inputStream);
            inputStream = request.getResourceAsStream("/WEB-INF/classes/mail.properties");
            mailProperties = new Properties();
            mailProperties.load(inputStream);
            LogConfiguration.LOGGER.debug("config resources loaded");
        } catch (IOException e) {
            LogConfiguration.LOGGER.error("can't config resources");
        }
        return true;
    }

    public static String getPathProperty(String key) {
        return System.getProperty("catalina.base") + "\\webapps\\" +config.getProperty(key);
    }
    public static String getProperty(String key) {
        return config.getProperty(key);
    }
}