package com.itechart.bukinevi.logic.database;

import com.itechart.bukinevi.logic.entity.ContactPhone;

import java.util.List;

/**
 * Created by aefrd on 28.09.2016.
 */
 public interface PhoneDAO{
     void addPhone(ContactPhone phone, final int EMPLOYEEID);
     List<ContactPhone> getPhoneList(int ID);
     void updatePhone(ContactPhone phone);

     void deletePhone(final int PHONEID);
}
