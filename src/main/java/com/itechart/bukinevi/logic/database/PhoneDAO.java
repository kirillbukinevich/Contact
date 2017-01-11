package com.itechart.bukinevi.logic.database;

import com.itechart.bukinevi.logic.entity.ContactPhone;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.itechart.bukinevi.logic.configuration.LogConfiguration.LOGGER;

/**
 * Created by aefrd on 28.09.2016.
 */
 interface PhoneDAO{
     boolean addPhone(ContactPhone phone, final int EMPLOYEEID);
     List<ContactPhone> getPhoneList(int ID);
     boolean updatePhone(ContactPhone phone);

     boolean deletePhone(final int PHONEID);
}
