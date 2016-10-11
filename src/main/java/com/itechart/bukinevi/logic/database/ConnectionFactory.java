//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.itechart.bukinevi.logic.database;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import static com.itechart.bukinevi.logic.configuration.LogConfiguration.LOGGER;

public class ConnectionFactory {
    private static Connection con;

    private ConnectionFactory() {
    }

    public static synchronized Connection getConnection() {
            try {
                Context ctx = new InitialContext();
                DataSource dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/EmployeesDS");
                con = dataSource.getConnection();
            } catch (NamingException e) {
                LOGGER.error(e);
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        return con;
    }
}
