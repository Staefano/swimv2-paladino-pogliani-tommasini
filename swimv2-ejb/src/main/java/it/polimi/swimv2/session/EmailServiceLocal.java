package it.polimi.swimv2.session;

import javax.ejb.Local;
import javax.mail.MessagingException;

@Local
public interface EmailServiceLocal {
	void sendEmail(String toAddress, String subj,  String text) throws MessagingException;
	void sendConfirmationEmail(String email, String token) throws MessagingException;
}
