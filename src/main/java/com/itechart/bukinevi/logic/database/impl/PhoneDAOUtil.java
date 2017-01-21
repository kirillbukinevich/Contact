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
public class PhoneDAOUtil extends AbstractDAO implements PhoneDAO {
    private static final Logger LOGGER = LogManager.getLogger(PhoneDAOUtil.class);


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
            LOGGER.error(String.format("can't add phone to BD %s", e));
        }finally {
            try {
                if(preparedStatement!=null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(String.format("can't close preparedStatement method  addphone to BD %s", e));
            }
        }
    }
    public List<ContactPhone> getPhoneList(int ID) {
        ArrayList<ContactPhone> phoneList = new ArrayList<>();
        String query = "SELECT * FROM phone WHERE phone.employee_id=?";
        try {
            updatePrepareStatement(query);
            preparedStatement.setInt(1,ID);
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
                contactPhone.setIsSaved(true);
                phoneList.add(contactPhone);
            }
        } catch (SQLException e) {
            LOGGER.error(String.format("can't get phone list %s",e));
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return phoneList;
    }
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
            LOGGER.info(String.format("update phone to BD id: %d", phone.getId()));
        } catch (SQLException e) {
            LOGGER.error(String.format("can't update phone to BD %s",e));
        }finally {
            try {
                if(preparedStatement!=null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(String.format("can't close preparedStatement method  updatephone to BD %s",e));
            }
        }
    }

    public void deletePhone(final int PHONEID) {
        String deleteSQL = "DELETE FROM phone WHERE phone.id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, PHONEID);
            preparedStatement.executeUpdate();
            LOGGER.info("delete phone to BD");
        } catch (SQLException e) {
            LOGGER.error(String.format("can't delete phone %s",e));
        }finally {
            try {
                if(preparedStatement!=null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
