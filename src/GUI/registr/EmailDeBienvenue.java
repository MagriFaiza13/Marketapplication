/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.registr;

import java.io.IOException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Elife-Kef-112
 */
public class EmailDeBienvenue {
    
    Session newSession = null;
    MimeMessage mimeMessage = null;

    public static void main(String args[]) throws AddressException, MessagingException, IOException {
//        EmailDeBienvenue mail = new EmailDeBienvenue();
//        mail.setupServerProperties();
//        mail.draftEmail("rabie.zouita@esprit.tn", "rabie");
//        mail.sendEmail();
    }

    public void sendEmail() throws MessagingException {
        String fromUser = "Faiza.Magri@esprit.tn";  //Enter sender email id
        String fromUserPassword = "E13621379";  //Enter sender gmail password , this will be authenticated by gmail smtp server
        String emailHost = "smtp.gmail.com";
        Transport transport = newSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, fromUserPassword);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
        System.out.println("Email successfully sent !");
    }

    public MimeMessage draftEmail(String email, String nom) throws AddressException, MessagingException, IOException {
        String[] emailReceipients = {email};  //Enter list of email recepients
        String emailSubject = "SiccaPlan";

        mimeMessage = new MimeMessage(newSession);

        for (int i = 0; i < emailReceipients.length; i++) {
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailReceipients[i]));
        }
        mimeMessage.setFrom(new InternetAddress("motdepasseoublier"));
        mimeMessage.setSubject(emailSubject);

        // CREATE MIMEMESSAGE
        // CREATE MESSAGE BODY PARTS
        // CREATE MESSAGE MULTIPART
        // ADD MESSAGE BODY PARTS ----> MULTIPART
        // FINALLY ADD MULTIPART TO MESSAGECONTENT i.e. mimeMessage object
        Multipart emailContent = new MimeMultipart();

        //Text body part
        MimeBodyPart textBodyPart = new MimeBodyPart();
        MimeBodyPart xx = new MimeBodyPart();
        xx.setText("Welcome to siccaplan" + " " + nom + " . ");

        //Attachment body part.
        //Attach body parts
        String filename = "bienvenue.png";
        DataSource source = new FileDataSource(filename);
        textBodyPart.setDataHandler(new DataHandler(source));
        textBodyPart.setFileName(filename);

        emailContent.addBodyPart(textBodyPart);
        emailContent.addBodyPart(xx);
        //Attach multipart to message
        mimeMessage.setContent(emailContent);
        return mimeMessage;
    }

    public void setupServerProperties() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port", "25");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        newSession = Session.getDefaultInstance(properties, null);
    }

}
    

