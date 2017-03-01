package com.itechart.bukinevi.logic.dao.mysqlImpl;

import com.itechart.bukinevi.logic.dao.AbstractDAO;
import com.itechart.bukinevi.logic.dao.PhotoDAO;
import com.itechart.bukinevi.logic.domain.Photo;
import com.itechart.bukinevi.logic.exceptions.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhotoDAOImpl extends AbstractDAO implements PhotoDAO {
    private static final Logger LOGGER = LogManager.getLogger(PhotoDAOImpl.class.getName());

    @Override
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
            LOGGER.error("can't get photo ",e);
            throw new DaoException("Не удаётся извлечь фото");
        } finally {
            this.closePreparedStatement("getPhoto");
        }
        return photo;
    }

    @Override
    public void updatePhoto(Photo photo) {
        final int EMPLOYEE_ID = photo.getEmployeeID();
        updatePrepareStatement("UPDATE photo SET photo_name=? WHERE employee_id=?");
        try {
            if (photo.isDeleted()) {
                this.preparedStatement.setString(1, null);
            } else {
                this.preparedStatement.setString(1, photo.getPhotoName());
            }
            this.preparedStatement.setInt(2, EMPLOYEE_ID);
            this.preparedStatement.executeUpdate();
            LOGGER.info("update photo to BD employee id: {}", photo.getEmployeeID());
        } catch (SQLException e) {
            LOGGER.error("can't update photo to BD ",e);
            throw new DaoException("Не удаётся обновить фото");
        } finally {
            this.closePreparedStatement("updatePhoto");
        }
    }

}
