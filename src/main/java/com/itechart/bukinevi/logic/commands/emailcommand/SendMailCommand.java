package com.itechart.bukinevi.logic.commands.emailcommand;

import com.itechart.bukinevi.logic.commands.emailcommand.template.TempalteContent;
import com.itechart.bukinevi.logic.commands.maincommands.ContactCommand;
import com.itechart.bukinevi.logic.configuration.ConfigurationManager;
import com.itechart.bukinevi.logic.processcommand.ActionCommand;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

/**
 * Created by aefrd on 16.09.2016.
 */
public class SendMailCommand implements ActionCommand {
    public String execute(HttpServletRequest request) {
        sendMessage(request);
        ContactCommand contactCommand = new ContactCommand();
        return contactCommand.execute(request);
    }

    private void sendMessage(HttpServletRequest request) {
        String from = ConfigurationManager.getProperty("admin.username");
        String pass = ConfigurationManager.getProperty("admin.password");
        String emails = request.getParameter("list_mail");
        String[] to = emails.split(" ");
        String subject = request.getParameter("theme");
        String body = request.getParameter("message");
        sendFromGMail(from, to, subject, body,request);
    }
    private void sendFromGMail(String from, String[] to, String subject, String body,HttpServletRequest request) {
        Properties props = ConfigurationManager.mailProperties;
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }
            for (InternetAddress toAddres : toAddress) {
                message.addRecipient(Message.RecipientType.TO, toAddres);
            }

            message.setSubject(subject);
            message.setText(body);
            MimeMultipart mimeMultipart = getTemplateContent(request);
            if(mimeMultipart!=null) {
                message.setContent(mimeMultipart);
            }


            Transport transport = session.getTransport("smtp");
            transport.connect(ConfigurationManager.getProperty("host"), ConfigurationManager.getProperty("admin.username"),
                    ConfigurationManager.getProperty("admin.password"));
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
    }
    private MimeMultipart getTemplateContent(HttpServletRequest request){
        String templateType = request.getParameter("template_type");
        if(!templateType.isEmpty()) {
            TempalteContent tempalteContent = new TempalteContent();
            tempalteContent.chooseTemplateType(templateType);
            return tempalteContent.getContent();
        }else {
            return null;
        }
    }
}
