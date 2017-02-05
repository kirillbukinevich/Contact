package com.itechart.bukinevi.logic.database.impl;

import com.itechart.bukinevi.logic.database.AbstractDAO;
import com.itechart.bukinevi.logic.database.PhoneDAO;
import com.itechart.bukinevi.logic.entity.ContactPhone;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aefrd on 28.09.2016.
 */
public class MySqlPhoneDAO extends AbstractDAO implements PhoneDAO {
    private static final Logger LOGGER = LogManager.getLogger(MySqlPhoneDAO.class);


    @Override
    public void addPhone(ContactPhone phone, final int EMPLOYEEID) {
        updatePrepareStatement("INSERT INTO phone(code_country,code_operator,number,type,comment,employee_id) " +
                "VALUES(?,?,?,?,?,?)");
        try {
            this.preparedStatement.setInt(1, phone.getCodeCountry());
            this.preparedStatement.setInt(2, phone.getCodeOperator());
            this.preparedStatement.setInt(3, phone.getNumber());
            this.preparedStatement.setString(4, phone.getType());
            this.preparedStatement.setString(5, phone.getComment());
            this.preparedStatement.setInt(6, EMPLOYEEID);
            this.preparedStatement.executeUpdate();
            LOGGER.info("add phone to BD");
            phone.setId(retriveId(preparedStatement));
        } catch (SQLException e) {
            LOGGER.error("can't add phone to BD ", e);
        }finally {
           this.closePreparedStatement("addPhone");
        }
    }

    @Override
    public List<ContactPhone> getPhoneList(int ID) {
        List<ContactPhone> phoneList = new ArrayList<>();
        String query = "SELECT * FROM phone WHERE phone.employee_id=?";
        try {
            updatePrepareStatement(query);
            this.preparedStatement.setInt(1,ID);
            ResultSet e = this.preparedStatement.executeQuery();

            while (e.next()) {
                ContactPhone contactPhone = new ContactPhone();
                contactPhone.setId(e.getInt(1));
                contactPhone.setEmployeeID(e.getInt(2));
                contactPhone.setCodeCountry(e.getInt(3));
                contactPhone.setCodeOperator(e.getInt(4));
                contactPhone.setNumber(e.getInt(5));
                contactPhone.setType(e.getString(6));
                contactPhone.setComment(e.getString(7));
                phoneList.add(contactPhone);
            }
        } catch (SQLException e) {
            LOGGER.error("can't get phone list ",e);
        } finally {
            this.closePreparedStatement("getPhoneList");
        }
        return phoneList;
    }

    @Override
    public void updatePhone(ContactPhone phone){
        String query = "UPDATE phone SET code_country=?,code_operator=?,number=?,type=?,comment=? " +
                "WHERE id=?";
        updatePrepareStatement(query);
        try {
            preparedStatement.setInt(1,phone.getCodeCountry());
            preparedStatement.setInt(2,phone.getCodeOperator());
            preparedStatement.setInt(3,phone.getNumber());
            preparedStatement.setString(4, phone.getType());
            preparedStatement.setString(5,phone.getComment());
            preparedStatement.setInt(6,phone.getId());
            preparedStatement.executeUpdate();
            LOGGER.info("update phone to BD id: {}", phone.getId());
        } catch (SQLException e) {
            LOGGER.error("can't update phone to BD ",e);
        }finally {
            this.closePreparedStatement("updatePhone");
        }
    }

    @Override
    public void deletePhones(List<ContactPhone> phones) {
        if(phones.isEmpty()){
            return;
        }
        String deleteSQL = "DELETE FROM phone WHERE phone.id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
            for (ContactPhone phone : phones) {
                preparedStatement.setInt(1, phone.getId());
                preparedStatement.addBatch();
                LOGGER.info("delete phone from BD: " + phone.getId());
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            LOGGER.error("can't delete phone ",e);
        }finally {
           this.closePreparedStatement("deletePhone");
        }
    }

    @Override
    public void insertOrUpdatePhone(List<ContactPhone> phones, final int EMPLOYEEID) {
        String query = "INSERT INTO phone (id,employee_id,code_country, code_operator, number, type, comment) VALUES (?,?,?,?,?,?,?)" +
                "ON DUPLICATE KEY " +
                "UPDATE employee_id = values(employee_id),code_country = values(code_country)," +
                " code_operator = values(code_operator), number = values(number), type = values(type), comment = values(comment)";
        updatePrepareStatement(query);
        try {
            for(ContactPhone phone : phones){
                preparedStatement.setInt(1, phone.getId());
                preparedStatement.setInt(2, EMPLOYEEID);
                preparedStatement.setInt(3, phone.getCodeCountry());
                preparedStatement.setInt(4, phone.getCodeOperator());
                preparedStatement.setInt(5, phone.getNumber());
                preparedStatement.setString(6, phone.getType());
                preparedStatement.setString(7, phone.getComment());
                preparedStatement.addBatch();
                LOGGER.info("updateOrInsert phone to BD id: " + phone.getId());
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            LOGGER.error("can't updateOrInsert phone to BD ", e);
        } finally {
            this.closePreparedStatement("insertOrUpdatePhone");
        }
    }
}
