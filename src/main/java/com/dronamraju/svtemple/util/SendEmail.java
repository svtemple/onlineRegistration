package com.dronamraju.svtemple.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by mdronamr on 3/14/17.
 */
public class SendEmail {
    private static final String SMTP_HOST_NAME = "smtpout.secureserver.net"; //smtp URL
    private static final int SMTP_HOST_PORT = 465; //port number
    private static String SMTP_AUTH_USER = "online@svtempleco.org"; //email_id of sender
    private static String SMTP_AUTH_PWD = "SVTemple2017"; //password of sender email_id

    public static void sendMail(String content, String toEmail, String recipients) {
        try {
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtps");
            props.put("mail.smtps.host", SMTP_HOST_NAME);
            props.put("mail.smtps.auth", "true");

            Session mailSession = Session.getDefaultInstance(props);
            mailSession.setDebug(true);
            Transport transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);

            message.setSubject("Sri Venkateswara Swamy Temple Of Colorado");
            message.setContent(content, "text/html");
            Address[] from = InternetAddress.parse("online@svtempleco.org");//Your domain email
            message.addFrom(from);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail)); //Send email To (Type email ID that you want to send)
            //message.addRecipient(Message.RecipientType.CC, new InternetAddress(recipients)); //Send email To (Type email ID that you want to send)

            transport.connect(SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD);
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
