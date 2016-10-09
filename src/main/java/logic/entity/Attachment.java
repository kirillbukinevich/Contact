//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package logic.entity;

import java.time.LocalDateTime;
import static logic.configuration.LogConfiguration.LOGGER;

public class Attachment implements Cloneable{
    private int id;
    private int employeeID;
    private String fileName;
    private LocalDateTime loadDate;
    private String comment;
    private boolean saved;
    private boolean deleted;
    private boolean updated;
    private byte[] attachment;

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

    public Attachment() {
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
            LOGGER.error("can't clon attachment: " + e);
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
