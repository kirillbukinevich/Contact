package com.itechart.bukinevi.logic.database.impl;

import com.itechart.bukinevi.logic.database.AbstractDAO;
import com.itechart.bukinevi.logic.database.PhotoDAO;
import com.itechart.bukinevi.logic.entity.Photo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhotoDAOUtil extends AbstractDAO implements PhotoDAO {
    private static final Logger LOGGER = LogManager.getLogger(PhotoDAOUtil.class.getName());

    public Photo getPhoto(final int ID) {
        String query = "select photo_name,employee_id from photo  WHERE photo.employee_id=?";
        updatePrepareStatement(query);

        Photo photo = new Photo();
        try {
            preparedStatement.setInt(1, ID);
            ResultSet e = preparedStatement.executeQuery();
            while (e.next()) {
                String photoName = e.getString(1);
                if (photoName != null) {
                    photo.setPhotoName(photoName);
                    photo.setSaved(true);
                }
                photo.setEmployeeID(e.getInt(2));
            }
        } catch (SQLException e) {
            LOGGER.error(String.format("can't get photo %s",e));
        } finally {
            this.closePreparedStatement("getPhoto");
        }
        return photo;
    }

    public void updatePhoto(Photo photo) {
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
            LOGGER.info(String.format("update photo to BD employee id: %d", photo.getEmployeeID()));
        } catch (SQLException e) {
            LOGGER.error(String.format("can't update photo to BD %s",e));
        } finally {
            this.closePreparedStatement("updatePhoto");
        }
    }

}
