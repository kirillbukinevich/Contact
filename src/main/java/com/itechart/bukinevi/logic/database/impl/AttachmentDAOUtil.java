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

    @Override
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
            LOGGER.error("can't add attachment to BD ", e);
        } finally {
            this.closePreparedStatement("addAttachment");
        }
    }

    @Override
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
            LOGGER.error("can't update attachment to BD ", e);
        } finally {
            this.closePreparedStatement("updateAttachment");
        }
        return -1;
    }

    @Override
    public List<Attachment> getAttachmentList(int ID) {
        List<Attachment> attachmentList = new ArrayList<>();
        String query = "select * from attachments WHERE employee_id  = " + ID;

        try {
            this.stmt = connection.createStatement();
            ResultSet e = this.stmt.executeQuery(query);

            while (e.next()) {
                Attachment attachment = new Attachment();
                attachment.setId(e.getInt(1));
                attachment.setEmployeeID(e.getInt(2));
                attachment.setFileName(e.getString(3));
                attachment.setLoadDate(String.valueOf(e.getTimestamp(4).toLocalDateTime()));
                attachment.setComment(e.getString(5));
                attachmentList.add(attachment);
            }
        } catch (SQLException e) {
            LOGGER.error("can't get attachment list ", e);
        } finally {
            this.closeStatement("getAttachmentList");
        }
        return attachmentList;
    }

    @Override
    public void deleteAttachments(List<Attachment> attachments) {
        if(attachments.isEmpty()){
            return;
        }
        String deleteSQL = "DELETE FROM attachments WHERE attachments.id = ?";

        try {
            preparedStatement = connection.prepareStatement(deleteSQL);
            for (Attachment attachment : attachments) {
                preparedStatement.setInt(1, attachment.getId());
                preparedStatement.addBatch();
                LOGGER.info("delete attachment from BD: " + attachment.getId());
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            LOGGER.error("can't delete attachment ", e);
        } finally {
            this.closePreparedStatement("deleteAttachment");
        }
    }

    @Override
    public void insertOrUpdatePhone(List<Attachment> attachments, final int EMPLOYEEID) {
        String query = "INSERT INTO attachments (id,employee_id,file_name, date_of_load,comment) " +
                "VALUES (?,?,?,?,?) " +
                "ON DUPLICATE KEY " +
                "UPDATE employee_id = values(employee_id),file_name = values(file_name)," +
                " date_of_load = values(date_of_load), comment = values(comment)";
        updatePrepareStatement(query);
        try {
            for (Attachment attachment : attachments) {
                if (!attachment.isDeleted()) {
                    preparedStatement.setInt(1, attachment.getId());
                    preparedStatement.setInt(2, EMPLOYEEID);
                    preparedStatement.setString(3, attachment.getFileName());
                    preparedStatement.setTimestamp(4, Timestamp.valueOf(attachment.getLoadDate()));
                    preparedStatement.setString(5, attachment.getComment());
                    preparedStatement.addBatch();
                    LOGGER.info(String.format("updateOrInsert attachment to BD id: %d", attachment.getId()));
                }
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            LOGGER.error("can't updateOrInsert attachment to BD ", e);
        } finally {
            this.closePreparedStatement("insertOrUpdatePhone");
        }
    }

}
