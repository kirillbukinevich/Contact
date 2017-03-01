package com.itechart.bukinevi.logic.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by aefrd on 20.09.2016.
 */
public class ConfigurationManager {
    private static final Logger LOGGER = LogManager.getLogger(ConfigurationManager.class);
    private static Properties config;
    public static Properties mailProperties;

    private ConfigurationManager() {
    }

    public static void initProperty(ServletContext request) {
        try {
            InputStream inputStream = request.getResourceAsStream("/WEB-INF/classes/config.properties");
            config = new Properties();
            config.load(inputStream);
            inputStream = request.getResourceAsStream("/WEB-INF/classes/mail.properties");
            mailProperties = new Properties();
            mailProperties.load(inputStream);
            LOGGER.info("config resources loaded");
        } catch (IOException e) {
            LOGGER.error("can't config resources");
        }
    }

    public static String getPathProperty(String key) {
        return System.getProperty("catalina.base") + "\\webapps\\" +config.getProperty(key);
    }
    public static String getProperty(String key) {
        return config.getProperty(key);
    }
}