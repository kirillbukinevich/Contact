package com.itechart.bukinevi.logic.database;

import com.itechart.bukinevi.logic.database.impl.MySqlAttachmentDAO;
import com.itechart.bukinevi.logic.database.impl.MySqlEmployeeDAO;
import com.itechart.bukinevi.logic.database.impl.MySqlPhoneDAO;
import com.itechart.bukinevi.logic.database.impl.MySqlPhotoDAO;

public class MySqlFactory {
    public EmployeeDAO getEmployeeDAO(){
        return new MySqlEmployeeDAO();
    }
    public AttachmentDAO getAttachmentDAO(){
        return new MySqlAttachmentDAO();
    }
    public PhoneDAO getPhoneDAO(){
        return new MySqlPhoneDAO();
    }
    public PhotoDAO getPhotoDAO(){
        return new MySqlPhotoDAO();
    }
}
