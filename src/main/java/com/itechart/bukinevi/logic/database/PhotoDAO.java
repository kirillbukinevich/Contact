package com.itechart.bukinevi.logic.database;

import com.itechart.bukinevi.logic.entity.Photo;

 public interface PhotoDAO{
     Photo getPhoto(final int ID);

     void updatePhoto(Photo photo);

}
