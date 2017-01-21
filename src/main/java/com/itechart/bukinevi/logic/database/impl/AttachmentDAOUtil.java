package com.itechart.bukinevi.logic.database.impl;

import com.itechart.bukinevi.logic.database.AbstractDAO;
import com.itechart.bukinevi.logic.database.AttachmentDAO;
import com.itechart.bukinevi.logic.entity.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AttachmentDAOUtil extends AbstractDAO implements AttachmentDAO {
    private static final Logger LOGGER = LogManager.getLogger(AttachmentDAOUtil.class.getName());

    public void addAttachment(Attachment attachment) {
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
            LOGGER.error(String.format("can't add attachment to BD %s", e));
        } finally {
            this.closePreparedStatement("addAttachment");
        }
    }

    public int updateAttachment(Attachment attachment) {
        String query = "UPDATE attachments SET file_name=?,date_of_load=?,comment=? WHERE id=?";
        updatePrepareStatement(query);
        try {
            this.preparedStatement.setString(1, attachment.getFileName());
            this.preparedStatement.setTimestamp(2, Timestamp.valueOf(attachment.getLoadDate()));
            this.preparedStatement.setString(3, attachment.getComment());
            this.preparedStatement.setInt(4, attachment.getId());
            this.preparedStatement.executeUpdate();
            return attachment.getId();
        } catch (SQLException e) {
            LOGGER.error(String.format("can't update attachment to BD %s", e));
        } finally {
            this.closePreparedStatement("updateAttachment");
        }
        return -1;
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
                attachment.setSaved(true);
                attachmentList.add(attachment);
            }
        } catch (SQLException e) {
            LOGGER.error(String.format("can't get attachment list %s", e));
        }finally {
            this.closeStatement("getAttachmentList");
        }
        return attachmentList;
    }

    public void deleteAttachment(final int ATTACHMENTID) {
        String deleteSQL = "DELETE FROM attachments WHERE attachments.id = ?";

        try {
            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, ATTACHMENTID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(String.format("can't delete attachment %s", e));
        } finally {
          this.closePreparedStatement("deleteAttachment");
        }
     }

}
