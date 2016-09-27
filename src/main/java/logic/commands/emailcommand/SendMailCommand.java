package logic.commands.emailcommand;

import logic.commands.emailcommand.template.GenerateTemplates;
import logic.commands.emailcommand.template.TempalteContent;
import logic.commands.maincommands.ContactCommand;
import logic.processcommand.ActionCommand;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by aefrd on 16.09.2016.
 */
public class SendMailCommand implements ActionCommand {
    public String execute(HttpServletRequest request) {
        sendMessage(request);
        ContactCommand contactCommand = new ContactCommand();
        String page = contactCommand.execute(request);
        return page;
    }

    private boolean sendMessage(HttpServletRequest request) {
        String from = Admin.USER_NAME;
        String pass = Admin.PASSWORD;
        String emails = request.getParameter("list_mail");
        System.out.println(emails);
        String[] to = emails.split(" ");
        System.out.println(Arrays.toString(to));
        String subject = request.getParameter("theme");
        String body = request.getParameter("message");
        sendFromGMail(from, pass, to, subject, body,request);
        return true;
    }
    private void sendFromGMail(String from, String pass, String[] to, String subject, String body,HttpServletRequest request) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }
            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }
            message.setSubject(subject);
            message.setContent(getTemplateContent(request));


            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }
    public MimeMultipart getTemplateContent(HttpServletRequest request){
        String templateType = request.getParameter("template_type");
        System.out.println(request.getParameter("template_type"));
        TempalteContent tempalteContent = new TempalteContent();
        tempalteContent.chooseTemplateType(templateType);
        return tempalteContent.getContent();
    }
}
