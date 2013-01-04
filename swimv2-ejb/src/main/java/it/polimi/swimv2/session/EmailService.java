package it.polimi.swimv2.session;

import java.util.Properties;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
public class EmailService implements EmailServiceLocal {

	@Resource(mappedName = "java:/Mail")
	private Session mailSession;
	
	@Resource(mappedName = "swimv2-configuration")
	private Properties configProperties;

	// TODO make the URL dynamic
	private static final String INNER_PATH = "/confirmation?token=";
	private static final String BASIC_URL = "http://localhost:8080/swimv2-web";
	private String url;
	
	public EmailService() {
		if(configProperties != null) {
			url = configProperties.getProperty("swimv2.basicurl");
		}
		if (url == null) url = BASIC_URL;
		url += INNER_PATH;
	}

	public void sendEmail(String toAddress, String subj,  String text) 
			throws MessagingException {

		Message message = new MimeMessage(mailSession);
		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(toAddress));
		message.setSubject(subj);
		message.setText(text);

		Transport.send(message);
	}
	
	public void sendConfirmationEmail(String email, String token) throws MessagingException {
		String msg = "You (" + email + ") - or someone posing as you - requested registration to the SWIM version 2 website.\n" + 
						"Please complete your registration by clicking on the following link and filling in the form:\n\n" +
						url + token;
		sendEmail(email, "SWIMv2 - Complete your registration", msg);
	}

}
