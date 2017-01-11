package com.itechart.bukinevi.logic.database;

import com.itechart.bukinevi.logic.entity.Photo;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.itechart.bukinevi.logic.configuration.LogConfiguration.LOGGER;

public class PhotoDAOUtil extends AbstractDAO implements PhotoDAO{
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
        } catch (SQLException var5) {
            LOGGER.error("can't get photo ", var5);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return photo;
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
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

}
