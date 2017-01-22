
package com.itechart.bukinevi.logic.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Attachment implements Cloneable,Serializable{
    private static final Logger LOGGER = LogManager.getLogger(Attachment.class.getName());

    private int id;
    private int employeeID;
    private String fileName;
    private LocalDateTime loadDate;
    private String comment;
    private boolean saved;
    private boolean deleted;
    private boolean updated;
    private byte[] attachment;

    public Attachment() {}

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLoadDate() {
        return this.loadDate.toString().replace('T',' ');
    }

    public void setLoadDate(LocalDateTime loadDate) {
        this.loadDate = loadDate;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isSaved() {
        return saved;
    }

    public void setSaved(boolean saved) {
        this.saved = saved;
    }

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    @Override
    public Attachment clone(){
        try {
            return (Attachment)super.clone();
        } catch (CloneNotSupportedException e) {
            LOGGER.error("can't clone attachment: ", e);
        }
        return new Attachment();
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", employeeID=" + employeeID +
                ", fileName='" + fileName + '\'' +
                ", saved=" + saved +
                ", deleted=" + deleted +
                ", updated=" + updated +
                "}\n";
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
