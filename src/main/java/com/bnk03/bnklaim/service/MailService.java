package com.bnk03.bnklaim.service;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

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

import com.bnk03.bnklaim.entity.Accounts;

import org.springframework.beans.factory.annotation.Value;

public class MailService {
    @Value("${bnklaim.mailservice.email}")
    private String senderEmail;

    @Value("${bnklaim.mailservice.password}")
    private String senderPassword;

    private Random random = new Random();

    public MailService() {
        // constructor
    }

    public void sendMail(Accounts accounts, String otp) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        String subject = "Here's your One Time Password (OTP) - Expire in 5 minutes!";

        String content = "<p>Hello " + accounts.getFirstName() + "</p>"
                + "<p>For security reason, you're required to use the following " + "One Time Password to login:</p>"
                + "<p><b>" + otp + "</b></p>" + "<br>" + "<p>Note: this OTP is set to expire in 5 minutes.</p>";

        Message message = new MimeMessage(session);
        System.out.println(senderEmail);
        System.out.println(senderPassword);
        message.setFrom(new InternetAddress(senderEmail, false));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(accounts.getEmail()));
        message.setSubject(subject);
        message.setSentDate(new Date());

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(content, "text/html");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);
        message.setContent(multipart);
        Transport.send(message);
    }

    public String generateOTP(int n) {
        String alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = random.nextInt(alphanumeric.length());
            System.out.println(index);
            sb.append(alphanumeric.charAt(index));
        }
        return sb.toString();
    }
}
