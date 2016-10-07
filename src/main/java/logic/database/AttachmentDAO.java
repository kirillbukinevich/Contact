package logic.database;

import logic.entity.Attachment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static logic.configuration.LogConfiguration.LOGGER;

public class AttachmentDAO extends AbstractDAO {
    public boolean addAttachment(Attachment attachment) {
        final int EMPLOYEEID = attachment.getEmployeeID();
        updatePrepareStatement("INSERT INTO attachments(file_name,date_of_load,comment,employee_id) " +
                "VALUES(?,?,?," + EMPLOYEEID + ")");
        try {
            this.preparedStatement.setString(1, attachment.getFileName());
            this.preparedStatement.setTimestamp(2, Timestamp.valueOf(attachment.getLoadDate()));
            this.preparedStatement.setString(3, attachment.getComment());
            this.preparedStatement.executeUpdate();
            attachment.setId(retriveId(preparedStatement));
        } catch (SQLException e) {
            LOGGER.error("can't add attachment to BD ", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                LOGGER.error("can't close preparedStatement method addAttachment to BD ", e);
            }
        }
        return true;
    }

    public boolean updateAttachment(Attachment attachment){
        String query = "UPDATE attachments SET file_name=?,date_of_load=?,comment=? WHERE id=?";
        updatePrepareStatement(query);
        try {
            this.preparedStatement.setString(1, attachment.getFileName());
            this.preparedStatement.setTimestamp(2, Timestamp.valueOf(attachment.getLoadDate()));
            this.preparedStatement.setString(3, attachment.getComment());
            this.preparedStatement.setInt(4,attachment.getId());
            this.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("can't update attachment to BD ", e);
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                LOGGER.error("can't close preparedStatement method updateAttachment to BD ", e);
            }
        }
        return true;
    }
    public List<Attachment> getAttachmentList(int ID) {
        ArrayList<Attachment> attachmentList = new ArrayList<>();
        String query = "select * from attachments WHERE employee_id  = " + ID;

        try {
            this.stmt = connection.createStatement();
            ResultSet e = this.stmt.executeQuery(query);

            while (e.next()) {
                Attachment attachment = new Attachment();
                attachment.setId(e.getInt(1));
                attachment.setEmployeeID(e.getInt(2));
                attachment.setFileName(e.getString(3));
                attachment.setLoadDate(e.getTimestamp(4).toLocalDateTime());
                attachment.setComment(e.getString(5));
                attachmentList.add(attachment);
            }
        } catch (SQLException var6) {
            LOGGER.error("can't get attachment list ", var6);
        }

        return attachmentList;
    }

    public boolean deleteAttachment(final int PHONEID) {
        String deleteSQL = "DELETE FROM attachments WHERE attachments.id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, PHONEID);
            preparedStatement.executeUpdate();
        } catch (SQLException var5) {
            LOGGER.error("can't delete attachment ", var5);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

}
