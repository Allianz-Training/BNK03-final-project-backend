package com.bnk03.bnklaim;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.bnk03.bnklaim.entities.UserAccounts;

public class EmailService {
    private EmailService() {
    }

    public static void sendMail(UserAccounts userAccounts, String otp) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("bnk03bnklaim@gmail.com", "Wins2020");
            }
        });

        String subject = "Here's your One Time Password (OTP) - Expire in 5 minutes!";

        String content = "<p>Hello " + userAccounts.getFirstName() + "</p>"
                + "<p>For security reason, you're required to use the following " + "One Time Password to login:</p>"
                + "<p><b>" + otp + "</b></p>" + "<br>" + "<p>Note: this OTP is set to expire in 5 minutes.</p>";

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("bnk03bnklaim@gmail.com", false));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("natthapong41@gmail.com"));
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(content, "text/html");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        msg.setContent(multipart);
        Transport.send(msg);
    }
}
