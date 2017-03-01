package com.itechart.bukinevi.logic.dao;

import com.itechart.bukinevi.logic.domain.Attachment;

import java.util.List;

public interface AttachmentDAO{
     void addAttachment(Attachment attachment);

     int updateAttachment(Attachment attachment);

     List<Attachment> getAttachmentList(int ID);

     void deleteAttachments(List<Attachment> attachments);

    void insertOrUpdateAttachments(List<Attachment> attachments, int EMPLOYEE_ID);
}
