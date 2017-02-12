package com.itechart.bukinevi.logic.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Attachment implements Serializable{

    private int id;
    private int employeeID;
    private String fileName;
    private LocalDateTime loadDate;
    private String comment;
    private byte[] bytes;
    private boolean isSaveOnDisk = true;
    private boolean isDeleted = false;

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
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
        return this.loadDate.toString().replace('T', ' ');
    }

    public void setLoadDate(String loadDate) {
        this.loadDate = LocalDateTime.parse(loadDate.replace(" ", "T"));
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
