package logic.commands.emailcommand;

import logic.database.EmployeeDAO;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;
import static logic.configuration.LogConfiguration.LOGGER;

public class BirthdayNotice implements Runnable {
    private void noticeAdmin(List<String> birthdayList) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", Admin.USER_NAME);
        props.put("mail.smtp.password", Admin.PASSWORD);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(Admin.USER_NAME));
            InternetAddress toAddres = new InternetAddress(Admin.USER_NAME);
            // To get the array of addresses
            message.addRecipient(Message.RecipientType.TO, toAddres);


            message.setSubject("Birthday notice");
            message.setText(birthdayList.toString());

//


            Transport transport = session.getTransport("smtp");
            transport.connect(host, Admin.USER_NAME, Admin.PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (MessagingException me) {
            LOGGER.error(me);
        }
    }

    public List<String> getBirthdayListFromDB() {
        EmployeeDAO contactDAO = new EmployeeDAO();
        return contactDAO.getBirthdayList();

    }

    @Override
    public void run() {
        List<String> birhdayList = getBirthdayListFromDB();
        if (birhdayList.isEmpty()) {
            return;
        }
        noticeAdmin(birhdayList);

    }
}
