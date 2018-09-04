package com.gmail.sshekh.dao.connection;


import com.gmail.sshekh.config.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionService {

    private static final Logger logger = LogManager.getLogger(ConnectionService.class);

    private static ConnectionService instance;

    private Connection connection;

    private ConnectionService() {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        logger.info("-------- MySQL JDBC Connection Testing ------------");
        try {
            Class.forName(configurationManager.getProperty(ConfigurationManager.DATABASE_DRIVER_NAME));
            logger.info("-------- MySQL JDBC Driver registered ------------");
        } catch (ClassNotFoundException e) {
            logger.error("Where is your MySQL JDBC Driver?", e);
        }
    }

    public static ConnectionService getInstance() {
        if (instance == null) {
            instance = new ConnectionService();
        }
        return instance;
    }

    public Connection getConnection() {
        logger.debug("Creating connection...");
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();
        try {
            Properties properties = new Properties();
            properties.setProperty("user", configurationManager.getProperty(ConfigurationManager.DATABASE_USERNAME));
            properties.setProperty("password", configurationManager.getProperty(ConfigurationManager.DATABASE_PWD));
            properties.setProperty("useUnicode", "true");
            properties.setProperty("characterEncoding", "cp1251");
            connection = DriverManager.getConnection(
                    configurationManager.getProperty(ConfigurationManager.DATABASE_URL),
                    properties
            );
            logger.info("Connection was created");
            return connection;
        } catch (SQLException e) {
            logger.error("Connection Failed! Check output console", e);
            throw new RuntimeException();
        }
    }
}
