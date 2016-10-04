package logic.commands.emailcommand.template;

import logic.configuration.ConfigurationManager;

import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;

/**
 * Created by aefrd on 27.09.2016.
 */
public class TempalteContent {
    private String attachImage;
    private String text;
    private String cid = ContentIdGenerator.getContentId();
    private MimeBodyPart imagePart = new MimeBodyPart();
    private MimeBodyPart textPart = new MimeBodyPart();
    private MimeMultipart content = new MimeMultipart("related");
    private String path = ConfigurationManager.getProperty("path.image");

    private boolean generateTemplateBirthdayManForSend() throws MessagingException, IOException {
        attachImage = path + "birthday/birthday_men.jpg";
        text = "We all hope you have a bright future, <br>" +
                "because you have a lot of challenges coming up in your life, <br>" +
                "you’re brave, smart, loving, and we know you will be able to get through them! <br>";
        content.addBodyPart(getTextPart());
        content.addBodyPart(getImagePart());
        return true;
    }
    private boolean generateTemplateBirthdayWomanForSend() throws MessagingException, IOException {
        attachImage = path + "birthday/birthday_women.jpg";
        text = "Today is a great day to celebrate a birthday,<br>\n" +
                "May joy and laughter never leave you<br>\n" +
                "And all your sorrows go away!<br>";
        content.addBodyPart(getTextPart());
        content.addBodyPart(getImagePart());
        return true;
    }

    public MimeMultipart getContent() {

        return content;
    }

    public void chooseTemplateType(String typeTemplate) {
        try {
            switch (typeTemplate) {
                case "С Днём рождения мужчинам":
                    generateTemplateBirthdayManForSend();
                    break;
                case "С Днём рождения женщинам":
                    generateTemplateBirthdayWomanForSend();
                    break;
                case "email":
                    //fillEmailTemplate();
                    break;
            }
        } catch (IOException | MessagingException e) {
            e.printStackTrace();
        }

    }



    private MimeBodyPart getImagePart()
            throws MessagingException, IOException {
        imagePart.attachFile(attachImage);

        imagePart.setContentID("<" + cid + ">");
        imagePart.setDisposition(MimeBodyPart.INLINE);
        return imagePart;
    }
    public MimeBodyPart getTextPart() throws MessagingException {
        String attachBody = "<html>"
                + "<body><div><b>" + text + "</b></div>"
                + "<img src=\"cid:"
                + cid
                + "\" /></div>\n" + "<div>I hope you like it!</div></body></html>";
        textPart.setText(attachBody,
                "US-ASCII", "html");
        return textPart;
    }





}
