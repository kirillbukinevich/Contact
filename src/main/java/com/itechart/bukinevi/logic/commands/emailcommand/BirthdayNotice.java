package com.itechart.bukinevi.logic.commands.emailcommand;

import com.itechart.bukinevi.logic.configuration.ConfigurationManager;
import com.itechart.bukinevi.logic.database.EmployeeDAO;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;
import static com.itechart.bukinevi.logic.configuration.LogConfiguration.LOGGER;

public class BirthdayNotice implements Runnable {
    private void noticeAdmin(List<String> birthdayList) {
        Properties props = ConfigurationManager.mailProperties;
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(ConfigurationManager.getProperty("admin.username")));
            InternetAddress toAddres = new InternetAddress(ConfigurationManager.getProperty("admin.password"));
            // To get the array of addresses
            message.addRecipient(Message.RecipientType.TO, toAddres);


            message.setSubject("Birthday notice");
            message.setText(birthdayList.toString());

//


            Transport transport = session.getTransport("smtp");
            transport.connect(ConfigurationManager.getProperty("host"), ConfigurationManager.getProperty("admin.username"), ConfigurationManager.getProperty("admin.password"));
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
