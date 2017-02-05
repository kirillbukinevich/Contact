package com.itechart.bukinevi.logic.commands.emailcommand.template;

import org.stringtemplate.v4.ST;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by aefrd on 23.09.2016.
 */
class GenerateTemplates {
    private String subject;
    private String text;
    private String includePage;

    public GenerateTemplates(){}

    public String getIncludePage() {
        return includePage;
    }

    public void setIncludePage(String includePage) {
        this.includePage = includePage;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }



    private ST getSTInstance() throws URISyntaxException, IOException {
        return  new ST(new String(Files.readAllBytes(Paths
                .get(GenerateTemplates.class.getResource(
                        "/birthday.html").toURI()))), '#', '#');
    }

    private void fillBirthdayManTemplate() {
        subject = "Happy Birthday!";
        text = "We all hope you have a bright future, <br>" +
                "because you have a lot of challenges coming up in your life, <br>" +
                "you’re brave, smart, loving, and we know you will be able to get through them! <br>";
        try {
            ST st = getSTInstance();
            st.add("image","<img class=\"img-responsive\" src=\"/zcontact/web/image/birthday/birthday_men.jpg\">");
            st.add("text",text);
            includePage = st.render();
            text = "";
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
    private void fillBirthdayWomanTemplate() {
        subject = "Happy Birthday!";
        text = "Today is a great day to celebrate a birthday,<br>\n" +
                "May joy and laughter never leave you<br>\n" +
                "And all your sorrows go away!<br>";
        try {
            ST st = getSTInstance();
            st.add("image","<img class=\"img-responsive\" src=\"/zcontact/web/image/birthday/birthday_women.jpg\">");
            st.add("text",text);
            includePage = st.render();
            text = "";
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void fillEmailTemplate() throws URISyntaxException, IOException {
        ST emailTestTemplate = new ST(new String(Files.readAllBytes(Paths
                .get(GenerateTemplates.class.getResource(
                        "/emailTemplate.html").toURI()))), '$', '$');
        emailTestTemplate.add("firstName", "John");
        emailTestTemplate.add("lastName", "Wracker");
        String emailContent = "Happy birthday"; // provider email content here
        emailTestTemplate.add("content", emailContent);
        emailTestTemplate.add("senderFullName", "Jeoff Desouza");
        includePage = emailTestTemplate.render();
    }


    void chooseTemplate(String templateType) {
        try {
            switch (templateType) {
                case "man_birthday":
                    fillBirthdayManTemplate();
                    break;
                case "woman_birthday":
                    fillBirthdayWomanTemplate();
                    break;
                case "email":
                    fillEmailTemplate();
                    break;
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String toString() {
        return subject;
    }

}
