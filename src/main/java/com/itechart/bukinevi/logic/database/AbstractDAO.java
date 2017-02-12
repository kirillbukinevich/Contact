package com.itechart.bukinevi.logic.database;

import com.itechart.bukinevi.logic.exceptions.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

/**
 * Created by aefrd on 28.09.2016.
 */
public abstract class AbstractDAO {
    protected static Connection connection = ConnectionFactory.getConnection();
    private static final Logger LOGGER = LogManager.getLogger(AbstractDAO.class.getName());
    protected Statement stmt;
    protected PreparedStatement preparedStatement;

    public void startEditContact() {
        try {
            connection.setAutoCommit(false);
            LOGGER.info("start edit contact");
        } catch (SQLException e) {
            LOGGER.error("can't start edit contact: ", e);
            throw new DaoException("Не удаётся начать транзакцию");
        }
    }

    public void saveContact() {
        try {
            if (!connection.getAutoCommit()) {
                connection.commit();
                connection.setAutoCommit(true);
                LOGGER.info("save contact");
            }
        } catch (SQLException e) {
            LOGGER.error("can't save contact: ", e);
            throw new DaoException("Не удаётся сохранить сотрудника");
        }
    }

    public void rollBack() {
        try {
            if (!connection.getAutoCommit()) {
                connection.rollback();
                connection.setAutoCommit(true);
                LOGGER.info("cancel edit contact");
            }
        } catch (SQLException e) {
            LOGGER.error("can't cancel edit contact: ", e);
            throw new DaoException("Не удаётся закрыть транзакцию");
        }
    }


    protected void updatePrepareStatement(String sqlQuery) {
        try {
            this.preparedStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException var3) {
            LOGGER.error("can't updatePrepareStatement: ",var3);
            throw new DaoException("Ошибка обращения к базе данных");
        }
    }

    protected int retriveId(PreparedStatement preparedStatement) throws SQLException {
        ResultSet rs = preparedStatement.getGeneratedKeys();
        int lastInsertedId = 0;
        if (rs.next()) {
            lastInsertedId = rs.getInt(1);
        }
        return lastInsertedId;
    }

    protected void closePreparedStatement(String methodName){
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            LOGGER.error("can't close preparedStatement method %s to BD {}", methodName);
            throw new DaoException("Не удаётся закрыть соединение");
        }

    }

    protected void closeStatement(String methodName){
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            LOGGER.error("can't close statement method %s to BD {}",methodName);
            throw new DaoException("Не удаётся закрыть соединение");
        }

    }

}
