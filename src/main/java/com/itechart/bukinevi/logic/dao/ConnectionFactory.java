//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.itechart.bukinevi.logic.dao;

import com.itechart.bukinevi.logic.exceptions.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionFactory.class);
    private static Connection con;

    private ConnectionFactory() {
    }

    static synchronized Connection getConnection() {
        try {
                Context ctx = new InitialContext();
                DataSource dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/EmployeesDS");
                con = dataSource.getConnection();
            } catch (NamingException e) {
                LOGGER.error(e);
                throw new DaoException("Ошибка имени драйвера");
            } catch (SQLException e) {
                LOGGER.error(e);
                throw new DaoException("Ошибка подключения к базе данных");
            }
        return con;
    }
}
