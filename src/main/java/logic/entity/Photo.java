package logic.entity;

/**
 * Created by aefrd on 13.09.2016.
 */
public class Photo {
    private int id;
    private int employeeID;
    private String photoName;
    private byte[] bytes;
    private boolean existInDB = false;
    private boolean deleted = false;

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isExistInDB() {
        return existInDB;
    }

    public void setExistInDB(boolean existInDB) {
        this.existInDB = existInDB;
    }

    public int getId() {
        return id;
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

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
