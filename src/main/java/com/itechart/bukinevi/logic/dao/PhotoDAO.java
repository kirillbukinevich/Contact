package com.itechart.bukinevi.logic.dao;

import com.itechart.bukinevi.logic.domain.Photo;

 public interface PhotoDAO{
     Photo getPhoto(final int ID);

     void updatePhoto(Photo photo);
 }
