package com.itechart.bukinevi.logic.database;

import com.itechart.bukinevi.logic.entity.Attachment;
import com.itechart.bukinevi.logic.entity.ContactPhone;

import java.util.ArrayList;
import java.util.List;

public interface AttachmentDAO{
     void addAttachment(Attachment attachment);

     int updateAttachment(Attachment attachment);

     List<Attachment> getAttachmentList(int ID);

     void deleteAttachments(List<Attachment> attachments);

    void insertOrUpdatePhone(List<Attachment> attachments, int EMPLOYEEID);
}
