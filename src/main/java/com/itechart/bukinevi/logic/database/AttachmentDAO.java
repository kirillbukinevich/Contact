package com.itechart.bukinevi.logic.database;

import com.itechart.bukinevi.logic.entity.Attachment;

import java.util.List;

public interface AttachmentDAO{
     void addAttachment(Attachment attachment);

     int updateAttachment(Attachment attachment);

     List<Attachment> getAttachmentList(int ID);

     void deleteAttachment(final int ATTACHMENTID);

}
