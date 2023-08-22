package services;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
	public void sendEmail(String sen, String rem, String dest, String msg, String ass) {
		Properties props = new Properties();
		props.put("mail.smtp.user", rem);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "25");
		//props.put("mail.debug", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.EnableSSL.enable", "true");

		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.port", "465");
		props.setProperty("mail.smtp.socketFactory.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(rem, sen);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(rem));

			Address[] toUser = InternetAddress.parse(dest);
			message.setRecipients(Message.RecipientType.TO, toUser);
			message.setSubject(ass);
			message.setContent(msg, "text/html; charset=utf-8");
			Transport.send(message);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}
}
