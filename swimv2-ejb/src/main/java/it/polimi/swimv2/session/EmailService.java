package it.polimi.swimv2.session;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
public class EmailService implements EmailServiceLocal {

	@Resource(mappedName = "java:/Mail")
	private Session mailSession;

	private static final String INNER_PATH = "/confirmation?token=";

	@Override
	public void sendEmail(String toAddress, String subj,  String text) 
			throws MessagingException {

		Message message = new MimeMessage(mailSession);
		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(toAddress));
		message.setSubject(subj);
		message.setText(text);

		Transport.send(message);
	}
	
	@Override
	public void sendConfirmationEmail(String email, String token, String uri) throws MessagingException {
		String msg = "You (" + email + ") - or someone posing as you - requested registration to the SWIM version 2 website.\n" + 
						"Please complete your registration by clicking on the following link and filling in the form:\n\n" +
						uri +  INNER_PATH + token;
		sendEmail(email, "SWIMv2 - Complete your registration", msg);
	}
	
	@Override
	public void sendResetEmail(String email, String token, String uri) throws MessagingException {
		String msg = "You (" + email + ") - or someone posing as you - requested to reset your password of the SWIM version 2 website.\n" + 
						"Please click on the following link to reset the password\n\n" +
						uri + INNER_PATH + token;
		sendEmail(email, "SWIMv2 - Password reset", msg);
	}

}
