package logic.database;

import logic.entity.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static logic.configuration.LogConfiguration.LOGGER;

public class EmployeeDAO {
    private static Connection connection = ConnectionFactory.getConnection();
    private ArrayList phoneList = new ArrayList();
    private ArrayList attachmentList = new ArrayList();
    private Statement stmt;
    private int noOfRecords;
    private PreparedStatement preparedStatement;


    public EmployeeDAO() {
    }

    public void startEditContact() {
        LOGGER.info("start edit contact");
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.error("can't start edit contact ", e);
        }
    }

    public boolean saveContact() {
        try {
            if (!connection.getAutoCommit()) {
                connection.commit();
                connection.setAutoCommit(true);
                LOGGER.info("save contact");
            }
        } catch (SQLException e) {
            LOGGER.error("can't save contact ", e);
        }
        return true;

    }

    public boolean rollBack() {
        try {
            if (!connection.getAutoCommit()) {
                connection.rollback();
                connection.setAutoCommit(true);
                LOGGER.info("cancel edit contact");
            }
        } catch (SQLException e) {
            LOGGER.error("can't cancel edit contact ", e);
        }
        return true;

    }

    public void updatePrepareStatement(String sqlQuery) {
        try {
            this.preparedStatement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException var3) {
            var3.printStackTrace();
        }
    }

    public boolean addPhone(ContactPhone phone, final int EMPLOYEEID) {
        updatePrepareStatement("INSERT INTO phone(code_country,code_operator,number,type,comment,employee_id) " +
                "VALUES(?,?,?,?,?," + EMPLOYEEID + ")");
        try {
            this.preparedStatement.setString(1, phone.getCodeCountry());
            this.preparedStatement.setString(2, phone.getCodeOperator());
            this.preparedStatement.setInt(3, phone.getNumber());
            this.preparedStatement.setString(4, phone.getType());
            this.preparedStatement.setString(5, phone.getComment());
            this.preparedStatement.executeUpdate();
            LOGGER.info("add phone to BD");
            phone.setId(retriveId(preparedStatement));
        } catch (SQLException e) {
            LOGGER.error("can't add phone to BD ", e);
        }finally {
            try {
                if(preparedStatement!=null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean addAttachment(Attachment attachment) {
        final int EMPLOYEEID = attachment.getEmployeeID();
        updatePrepareStatement("INSERT INTO attachments(file_name,date_of_load,comment,employee_id) " +
                "VALUES(?,?,?," + EMPLOYEEID + ")");
        try {
            this.preparedStatement.setString(1, attachment.getFileName());
            this.preparedStatement.setTimestamp(2, Timestamp.valueOf(attachment.getLoadDate()));
            this.preparedStatement.setString(3, attachment.getComment());
            this.preparedStatement.executeUpdate();
            LOGGER.info("add attachment to BD");
            attachment.setId(retriveId(preparedStatement));
        } catch (SQLException e) {
            LOGGER.error("can't add attachment to BD ", e);
        }finally {
            try {
                if(preparedStatement!=null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean updatePhoto(Photo photo) {
        final int EMPLOYEEID = photo.getEmployeeID();
        updatePrepareStatement("UPDATE photo SET photo_name=? WHERE employee_id=?");
        try {
            if (photo.isDeleted()) {
                this.preparedStatement.setString(1, null);
            } else {
                this.preparedStatement.setString(1, photo.getPhotoName());
            }
            this.preparedStatement.setInt(2, EMPLOYEEID);
            this.preparedStatement.executeUpdate();
            LOGGER.info("update photo to BD");
        } catch (SQLException e) {
            LOGGER.error("can't update photo to BD ", e);
        }finally {
            try {
                if(preparedStatement!=null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean editEmployee(Employee employee) {
        this.updatePrepareStatement("UPDATE main_info SET first_name=?,last_name=?,patronymic=?," +
                "date_of_birth=?,gender=?,nationality=?,family_status=?,web_site=?,email=?,work_place=? WHERE id=?");
        try {
            System.out.println(employee.getFirstName()+" "+employee.getLastName()+" "+employee.getPatronymic());
            this.preparedStatement.setString(1, employee.getFirstName());
            this.preparedStatement.setString(2, employee.getLastName());
            this.preparedStatement.setString(3, employee.getPatronymic());
            this.preparedStatement.setDate(4, Date.valueOf(employee.getDateOfBirth()));
            this.preparedStatement.setString(5, employee.getGender());
            this.preparedStatement.setString(6, employee.getNationality());
            this.preparedStatement.setString(7, employee.getFamilyStatus());
            this.preparedStatement.setString(8, employee.getWebSite());
            this.preparedStatement.setString(9, employee.getEmail());
            this.preparedStatement.setString(10, employee.getWorkPlace());
            this.preparedStatement.setInt(11, employee.getId());
            this.preparedStatement.executeUpdate();
            LOGGER.info("update employee to BD");
            this.editAddress(employee.getAddress(), employee.getId());
        } catch (SQLException e) {
            LOGGER.info("can't update employee to BD ", e);
        }finally {
            try {
                if(preparedStatement!=null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean editAddress(Address address, final int EMPLOYEEID) {
        updatePrepareStatement("UPDATE address SET country=?,city=?,street=?,house=?," +
                "flat=?,index_address=? WHERE employee_id=? ");
        try {
            this.preparedStatement.setString(1, address.getCountryName());
            this.preparedStatement.setString(2, address.getCityName());
            this.preparedStatement.setString(3, address.getStreetName());
            this.preparedStatement.setInt(4, address.getHouseNumber());
            this.preparedStatement.setInt(5, address.getFlatNumber());
            this.preparedStatement.setInt(6, address.getIndex());
            this.preparedStatement.setInt(7, EMPLOYEEID);
            this.preparedStatement.executeUpdate();
            LOGGER.info("update address to BD");
        } catch (SQLException e) {
            LOGGER.error("can't update address to BD ", e);
        }finally {
            try {
                if(preparedStatement!=null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public List<String> getBirthdayList() {
        LinkedList<String> birthdayList = new LinkedList<>();
        try {
            String query = "SELECT first_name,patronymic,last_name FROM main_info " +
                    "WHERE MONTH(date_of_birth) = MONTH(NOW()) AND DAY(date_of_birth) = DAY(NOW())";
            stmt = connection.createStatement();

            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                birthdayList.add(resultSet.getString(1) + " " +
                        resultSet.getString(2) + " " + resultSet.getString(3));
            }
        } catch (SQLException e) {
            LOGGER.error("can't get birthdayList: " + e.getMessage());
        }finally {
            try {
                if(preparedStatement!=null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return birthdayList;
    }

    public List<Employee> getEmployeesList(int offset, int recordsPerPage, String criteria) {
        String query = "select SQL_CALC_FOUND_ROWS * from main_info "
                + criteria + " limit " + offset + ", " + recordsPerPage;

        ArrayList list = new ArrayList();

        try {
            Statement e = connection.createStatement();
            ResultSet rs = e.executeQuery(query);
            rs = e.executeQuery("SELECT FOUND_ROWS()");
            if (rs.next()) {
                this.noOfRecords = rs.getInt(1);
                System.out.println("FOUND_ROWS: " + noOfRecords);
            }
            rs = e.executeQuery(query);
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setPatronymic(rs.getString("patronymic"));
                employee.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                employee.setGender(rs.getString("gender"));
                employee.setNationality(rs.getString("nationality"));
                employee.setFamilyStatus(rs.getString("family_status"));
                employee.setWebSite(rs.getString("web_site"));
                employee.setEmail(rs.getString("email"));
                employee.setWorkPlace(rs.getString("work_place"));
                employee.setAddress(this.retriveAddress(employee.getId()));
                employee.setAttachmentList(getAttachmentList(employee.getId()));
                employee.setPhoneList(getPhoneList(employee.getId()));
                list.add(employee);
            }



        } catch (SQLException var8) {
            LOGGER.error("can't get employee list ", var8);
        }

        return list;
    }

    public String getEmail(final int ID) {
        String query = "SELECT email FROM main_info WHERE id = ?";
        String email = new String();
        try {
            updatePrepareStatement(query);
            preparedStatement.setInt(1, ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            email = resultSet.getString(1);
        } catch (SQLException e) {
            LOGGER.error("can't get email ", e);
        }
        return email;
    }

    public ArrayList<ContactPhone> getPhoneList(int ID) {
        String query = "SELECT * FROM phone WHERE phone.employee_id=?";
        try {
            updatePrepareStatement(query);
            preparedStatement.setInt(1,ID);
            ResultSet e = this.preparedStatement.executeQuery();

            while (e.next()) {
                ContactPhone contactPhone = new ContactPhone();
                contactPhone.setId(e.getInt(1));
                contactPhone.setEmployeeID(e.getInt(2));
                contactPhone.setCodeCountry(e.getString(3));
                contactPhone.setCodeOperator(e.getString(4));
                contactPhone.setNumber(e.getInt(5));
                contactPhone.setType(e.getString(6));
                contactPhone.setComment(e.getString(7));
                phoneList.add(contactPhone);
            }
        } catch (SQLException var6) {
            LOGGER.error("can't get phone list ", var6);
        }

        return phoneList;
    }

    public int getNoOfRecords() {

        return this.noOfRecords;
    }

    public Employee getEmployeeOnId(int ID) {
        String query = "select * from main_info WHERE main_info.id=?";
        updatePrepareStatement(query);
        Employee employee = null;
        try {
            preparedStatement.setInt(1,ID);
            ResultSet e = preparedStatement.executeQuery();
            if (e.next()) {
                employee = new Employee();
                employee.setId(e.getInt("id"));
                employee.setFirstName(e.getString("first_name"));
                employee.setLastName(e.getString("last_name"));
                employee.setPatronymic(e.getString("patronymic"));
                employee.setDateOfBirth(e.getDate("date_of_birth").toLocalDate());
                employee.setGender(e.getString("gender"));
                employee.setNationality(e.getString("nationality"));
                employee.setFamilyStatus(e.getString("family_status"));
                employee.setWebSite(e.getString("web_site"));
                employee.setEmail(e.getString("email"));
                employee.setWorkPlace(e.getString("work_place"));
                employee.setAddress(this.retriveAddress(employee.getId()));
                employee.setAttachmentList(getAttachmentList(employee.getId()));
                employee.setPhoneList(getPhoneList(employee.getId()));
                Photo photo = getPhoto(employee.getId());
                employee.setPhoto(photo);
            }
        } catch (SQLException var5) {
            LOGGER.error("can't get employee on id ", var5);
        }

        return employee;
    }

    public int getNewEmployeeID() {
        try {
            this.updatePrepareStatement("INSERT INTO main_info (id) VALUES (NULL)");
            this.preparedStatement.executeUpdate();
            final int EMPLOYEEID = retriveId(preparedStatement);
            this.updatePrepareStatement("INSERT INTO address (employee_id) VALUES (LAST_INSERT_ID())");
            this.preparedStatement.executeUpdate();
            this.updatePrepareStatement("INSERT INTO photo (employee_id) VALUES (?)");
            this.preparedStatement.setInt(1, EMPLOYEEID);
            this.preparedStatement.executeUpdate();
            return EMPLOYEEID;


        } catch (SQLException e) {
            LOGGER.error("can't get new employee id ", e);
        }finally {
            try {
                if(preparedStatement!=null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public ArrayList<Attachment> getAttachmentList(int ID) {
        String query = "select * from attachments WHERE employee_id  = " + ID;

        try {
            this.stmt = connection.createStatement();
            ResultSet e = this.stmt.executeQuery(query);

            while (e.next()) {
                Attachment attachment = new Attachment();
                attachment.setId(e.getInt(1));
                attachment.setEmployeeID(e.getInt(2));
                attachment.setFileName(e.getString(3));
                attachment.setLoadDate(e.getTimestamp(4).toLocalDateTime());
                attachment.setComment(e.getString(5));
                attachmentList.add(attachment);
            }
        } catch (SQLException var6) {
            LOGGER.error("can't get attachment list ", var6);
        }

        return attachmentList;
    }

    public Photo getPhoto(final int ID) {
        String query = "select photo_name,employee_id from photo  WHERE photo.employee_id=?";
        updatePrepareStatement(query);

        Photo photo = new Photo();
        try {
            preparedStatement.setInt(1,ID);
            ResultSet e = preparedStatement.executeQuery();
            while (e.next()) {
                String photoName = e.getString(1);
                if (photoName != null) {
                    photo.setPhotoName(photoName);
                    photo.setExistInDB(true);
                }
                photo.setEmployeeID(e.getInt(2));
            }
        } catch (SQLException var5) {
            LOGGER.error("can't get photo ", var5);
        }finally {
            try {
                if(preparedStatement!=null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return photo;
    }

    public Address retriveAddress(int ID) {
        String query = "select * from address WHERE address.employee_id=" + ID;
        Address address = null;

        try {
            this.stmt = connection.createStatement();
            ResultSet e = this.stmt.executeQuery(query);

            while (e.next()) {
                address = new Address();
                address.setId(e.getInt("id"));
                address.setCountryName(e.getString("country"));
                address.setCityName(e.getString("city"));
                address.setStreetName(e.getString("street"));
                address.setHouseNumber(e.getInt("house"));
                address.setFlatNumber(e.getInt("flat"));
                address.setIndex(e.getInt("index_address"));
            }
        } catch (SQLException var5) {
            LOGGER.error("can't retrieve address ", var5);
        }

        return address;
    }

    public int retriveId(PreparedStatement preparedStatement) throws SQLException {
        ResultSet rs = preparedStatement.getGeneratedKeys();
        int last_inserted_id = 0;
        if (rs.next()) {
            last_inserted_id = rs.getInt(1);
        }
        return last_inserted_id;
    }

    public boolean deletePhone(final int PHONEID) {
        String deleteSQL = "DELETE FROM phone WHERE phone.id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, PHONEID);
            preparedStatement.executeUpdate();
        } catch (SQLException var5) {
            LOGGER.error("can't delete phone ", var5);
        }finally {
            try {
                if(preparedStatement!=null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    public boolean deleteAttachment(final int PHONEID) {
        String deleteSQL = "DELETE FROM attachments WHERE attachments.id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, PHONEID);
            preparedStatement.executeUpdate();
        } catch (SQLException var5) {
            LOGGER.error("can't delete attachment ", var5);
        }finally {
            try {
                if(preparedStatement!=null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    public boolean deleteAllContactPhone(final int EMPLOYEEID) throws SQLException {
        String deleteSQL = "DELETE FROM phone WHERE phone.employee_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
        preparedStatement.setInt(1, EMPLOYEEID);
        preparedStatement.executeUpdate();

        return true;

    }

    public boolean deleteAllAttachments(final int EMPLOYEEID) throws SQLException {
        String deleteSQL = "DELETE FROM attachments WHERE attachments.employee_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
        preparedStatement.setInt(1, EMPLOYEEID);
        preparedStatement.executeUpdate();

        return true;
    }

    public boolean deletePhoto(final int ID) throws SQLException {
        String deleteSQL = "DELETE FROM photo WHERE photo.employee_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
        preparedStatement.setInt(1, ID);
        preparedStatement.executeUpdate();

        return true;

    }

    public boolean deleteEmployee(int ID) {
        String deleteSQL = "DELETE main_info, address FROM main_info " +
                "INNER JOIN address ON main_info.id = address.employee_id WHERE main_info.id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, ID);
            preparedStatement.executeUpdate();
            deleteAllAttachments(ID);
            deleteAllContactPhone(ID);
            deletePhoto(ID);
        } catch (SQLException var5) {
            LOGGER.error("can't delete employee ", var5);
        }finally {
            try {
                if(preparedStatement!=null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    public void closeConnect() {
        try {
            if (this.stmt != null) {
                this.stmt.close();
            }

            if (connection != null) {
                connection.close();
            }
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

    }
}