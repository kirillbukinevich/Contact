package com.itechart.bukinevi.logic.database;

import com.itechart.bukinevi.logic.entity.Attachment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static com.itechart.bukinevi.logic.configuration.LogConfiguration.LOGGER;

 interface AttachmentDAO{
     boolean addAttachment(Attachment attachment);

     boolean updateAttachment(Attachment attachment);

     List<Attachment> getAttachmentList(int ID);

     boolean deleteAttachment(final int ATTACHMENTID);

}
