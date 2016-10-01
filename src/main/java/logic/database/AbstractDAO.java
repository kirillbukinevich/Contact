package logic.database;

import java.sql.*;

import static logic.configuration.LogConfiguration.LOGGER;

/**
 * Created by aefrd on 28.09.2016.
 */
public abstract class AbstractDAO {
    protected static Connection connection = ConnectionFactory.getConnection();
    protected Statement stmt;
    protected PreparedStatement preparedStatement;

    public void startEditContact() {
        LOGGER.info("start edit contact");
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.error("can't start edit contact ", e);
        }
    }

    public boolean saveContact() {
        try {
            if (!connection.getAutoCommit()) {
                connection.commit();
                connection.setAutoCommit(true);
                LOGGER.info("save contact");
            }
        } catch (SQLException e) {
            LOGGER.error("can't save contact ", e);
        }
        return true;

    }

    public boolean rollBack() {
        try {
            if (!connection.getAutoCommit()) {
                connection.rollback();
                connection.setAutoCommit(true);
                LOGGER.info("cancel edit contact");
            }
        } catch (SQLException e) {
            LOGGER.error("can't cancel edit contact ", e);
        }
        return true;

    }


    public void updatePrepareStatement(String sqlQuery) {
        try {
            this.preparedStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException var3) {
            var3.printStackTrace();
        }
    }

    public int retriveId(PreparedStatement preparedStatement) throws SQLException {
        ResultSet rs = preparedStatement.getGeneratedKeys();
        int last_inserted_id = 0;
        if (rs.next()) {
            last_inserted_id = rs.getInt(1);
        }
        return last_inserted_id;
    }

}
