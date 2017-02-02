
package com.itechart.bukinevi.logic.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Attachment implements Cloneable,Serializable{
    private static final Logger LOGGER = LogManager.getLogger(Attachment.class.getName());

    private int id;
    private int employeeID;
    private String fileName;
    private LocalDateTime loadDate;
    private String comment;
    private byte[] attachment;
    private boolean isSaveOnDisk = true;
    private boolean isDeleted = false;
    public Attachment() {}

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

    public void setLoadDate(String loadDate) {
        this.loadDate = LocalDateTime.parse(loadDate.replace(" ","T"));
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isSaveOnDisk() {
        return isSaveOnDisk;
    }

    public void setSaveOnDisk(boolean saveOnDisk) {
        isSaveOnDisk = saveOnDisk;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
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
                ", loadDate=" + loadDate +
                ", comment='" + comment + '\'' +
                ", isSaveOnDisk=" + isSaveOnDisk +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
