package com.example.demo.service;

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

import org.springframework.stereotype.Service;

import com.example.demo.models.dto.MessageDto;

@Service
public class MailService {

	public boolean sendMail(MessageDto messageDto) {
		boolean res = false;

		final String username = "go.switch.test@gmail.com";
		final String password = "123456789@switch";

		Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from.mail.id@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("amarasalim29@gmail.com"));
			message.setSubject(messageDto.getSujet());
			message.setText("PFA");

			MimeBodyPart messageBodyNom = new MimeBodyPart();
			MimeBodyPart messageBodyPhoneNumber = new MimeBodyPart();
			MimeBodyPart messageBodyEmail = new MimeBodyPart();
			MimeBodyPart messageBodyMessage = new MimeBodyPart();

			Multipart multipart = new MimeMultipart();

			messageBodyNom.setContent(messageDto.getNom(), "text/html");
			messageBodyPhoneNumber.setContent(messageDto.getPhoneNumber(), "text/html");
			messageBodyEmail.setContent(messageDto.getEmail(), "text/html");
			messageBodyMessage.setContent(messageDto.getMessage(), "text/html");
			multipart.addBodyPart(messageBodyNom);
			multipart.addBodyPart(messageBodyPhoneNumber);
			multipart.addBodyPart(messageBodyEmail);
			multipart.addBodyPart(messageBodyMessage);
			message.setContent(multipart);

			System.out.println("Sending mail...");

			Transport.send(message);

			System.out.println("Done");
			res = true;

		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return res;
	}
}
