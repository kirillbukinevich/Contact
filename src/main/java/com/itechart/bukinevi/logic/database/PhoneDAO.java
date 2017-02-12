package com.itechart.bukinevi.logic.database;

import com.itechart.bukinevi.logic.entity.ContactPhone;

import java.util.List;

/**
 * Created by aefrd on 28.09.2016.
 */
public interface PhoneDAO {
    void addPhone(ContactPhone phone, final int EMPLOYEE_ID);

    List<ContactPhone> getPhoneList(int ID);

    void updatePhone(ContactPhone phone);

    void deletePhones(List<ContactPhone> phones);

    void insertOrUpdatePhones(List<ContactPhone> phones, final int EMPLOYEE_ID);
}
