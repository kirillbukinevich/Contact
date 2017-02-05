package com.itechart.bukinevi.logic.commands.emailcommand;

import com.itechart.bukinevi.logic.configuration.ConfigurationManager;
import com.itechart.bukinevi.logic.database.impl.MySqlEmployeeDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class BirthdayNotice implements Runnable {
    private static final Logger LOGGER = LogManager.getLogger(BirthdayNotice.class);

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

    private List<String> getBirthdayListFromDB() {
        MySqlEmployeeDAO contactDAO = new MySqlEmployeeDAO();
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
