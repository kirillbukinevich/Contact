package logic.entity;

import java.io.Serializable;

public class ContactPhone implements Cloneable,Serializable{
    private Integer id;
    private Integer employeeID;
    private Integer codeCountry;
    private Integer codeOperator;
    private Integer number;
    private String type;
    private String comment;
    private boolean isSaved;
    private boolean isDeleted;
    private boolean isUpdated;
    public ContactPhone() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public Integer getCodeCountry() {
        return this.codeCountry;
    }

    public void setCodeCountry(Integer codeCountry) {
        this.codeCountry = codeCountry;
    }

    public Integer getCodeOperator() {
        return this.codeOperator;
    }

    public void setCodeOperator(Integer codeOperator) {
        this.codeOperator = codeOperator;
    }

    public Integer getNumber() {
        return this.number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setIsSaved(boolean isSaved) {
        this.isSaved = isSaved;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public boolean isUpdated() {
        return isUpdated;
    }

    public void setIsUpdated(boolean isUpdated) {
        this.isUpdated = isUpdated;
    }

    @Override
    public ContactPhone clone() {
        try {
            return (ContactPhone) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return new ContactPhone();
    }

    @Override
    public String toString() {
        return "ContactPhone{" +
                "id=" + id +
                ", employeeID=" + employeeID +
                ", isSaved=" + isSaved +
                ", isDeleted=" + isDeleted +
                ", isUpdated=" + isUpdated +
                  " " + number + "hashcode=" + hashCode() + "}" + '\n';
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
