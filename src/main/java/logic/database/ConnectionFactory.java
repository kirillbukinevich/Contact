//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package logic.database;

import logic.configuration.ConfigurationManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static ConnectionFactory instance = new ConnectionFactory();
    String url = ConfigurationManager.getProperty("databaseUrl");
    String user = ConfigurationManager.getProperty("userName");
    String password = ConfigurationManager.getProperty("userPassword");
    String driverClass = "com.mysql.jdbc.Driver";

    private ConnectionFactory() {
        try {
            Class.forName(this.driverClass);
        } catch (ClassNotFoundException var2) {
            var2.printStackTrace();
        }

    }

    public static ConnectionFactory getInstance() {
        return instance;
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection connection = DriverManager.getConnection(this.url, this.user, this.password);
        return connection;
    }
}
