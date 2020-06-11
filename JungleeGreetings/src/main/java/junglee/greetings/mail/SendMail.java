package junglee.greetings.mail;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendMail {
	
	@Autowired
	private EmailConfig emailconf;

	public boolean SendWithoutAttachement(String from, String to, String subject, String body) {
		
		Properties properties = new Properties();
		
		properties.put("mail.smtp.host", emailconf.getHost());
        properties.put("mail.smtp.port", emailconf.getPort());
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", emailconf.getUsername());
        properties.put("mail.password", emailconf.getPassword());
        
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(emailconf.getUsername(), emailconf.getPassword());
            }
            });
        try {
               MimeMessage message = new MimeMessage(session);
               message.setFrom(new InternetAddress(from));
               message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
               message.setSubject(subject);
               message.setContent(body, "text/html");
               Transport.send(message);
               return true;
            } catch (MessagingException mex) {
               mex.printStackTrace();
               return false;
            }
	}
		
	
	public boolean sendWithAttachement(String from, String to, List<String> cc, String subject, File fileContent) {
		
        Properties properties = new Properties();
        
        properties.put("mail.smtp.host", emailconf.getHost());
        properties.put("mail.smtp.port", emailconf.getPort());
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", emailconf.getUsername());
        properties.put("mail.password", emailconf.getPassword());
        
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(emailconf.getUsername(), emailconf.getPassword());
            }
        };
	    
        Session session = Session.getInstance(properties, auth);
	    
	  		MimeMessage msg = new MimeMessage(session);
	  		try {
	  			
	  			msg.setFrom(new InternetAddress(from));
	  			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	  			msg.setSubject(subject);
	  			while(cc.size() > 0) {
	  				msg.addRecipients(Message.RecipientType.CC, cc.get(0));
	  				cc.remove(0);
	  			}
	  			
	  			Multipart emailContent = new MimeMultipart();
	  			
	  			MimeBodyPart body = new MimeBodyPart();
	  			try {
					body.attachFile(fileContent);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	  			//body.setContent(fileContent, "jpg");	
	  	
	  			emailContent.addBodyPart(body);
	  			msg.setContent(emailContent);
	  			
	  			Transport.send(msg);
	  			System.out.println("Sent message");
	  		} catch (MessagingException e) {
	  			e.printStackTrace();
	  		}
		return true;
		
	}
	
public boolean SendWithoutAttachement1(String from, String to, List<String> cc, String subject, String Image) {
	
		String htmlText = "<img src=\"/home/welcomeGreeting.jpg\">";

		Properties properties = new Properties();
		
		properties.put("mail.smtp.host", emailconf.getHost());
        properties.put("mail.smtp.port", emailconf.getPort());
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", emailconf.getUsername());
        properties.put("mail.password", emailconf.getPassword());
        
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(emailconf.getUsername(), emailconf.getPassword());
            }
            });
        try {
               MimeMessage message = new MimeMessage(session);
               message.setFrom(new InternetAddress(from));
               message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
               message.setSubject(subject);
               message.setContent(htmlText, "text/html");
               Transport.send(message);
               return true;
            } catch (MessagingException mex) {
               mex.printStackTrace();
               return false;
            }
	}

}
