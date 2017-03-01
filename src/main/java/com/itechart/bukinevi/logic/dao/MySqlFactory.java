package com.itechart.bukinevi.logic.dao;

import com.itechart.bukinevi.logic.dao.mysqlImpl.AttachmentDAOImpl;
import com.itechart.bukinevi.logic.dao.mysqlImpl.EmployeeDAOImpl;
import com.itechart.bukinevi.logic.dao.mysqlImpl.PhoneDAOImpl;
import com.itechart.bukinevi.logic.dao.mysqlImpl.PhotoDAOImpl;

public class MySqlFactory {
    public EmployeeDAO getEmployeeDAO(){
        return new EmployeeDAOImpl();
    }
    public AttachmentDAO getAttachmentDAO(){
        return new AttachmentDAOImpl();
    }
    public PhoneDAO getPhoneDAO(){
        return new PhoneDAOImpl();
    }
    public PhotoDAO getPhotoDAO(){
        return new PhotoDAOImpl();
    }
}
