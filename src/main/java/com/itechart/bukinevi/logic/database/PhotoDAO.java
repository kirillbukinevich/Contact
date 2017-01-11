package com.itechart.bukinevi.logic.database;

import com.itechart.bukinevi.logic.entity.Photo;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.itechart.bukinevi.logic.configuration.LogConfiguration.LOGGER;

 interface PhotoDAO{
     Photo getPhoto(final int ID);

     boolean updatePhoto(Photo photo);

}
