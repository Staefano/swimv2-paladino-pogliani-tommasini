package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.exceptions.NoSuchUserException;
import it.polimi.swimv2.session.exceptions.NotUniqueException;

import javax.ejb.Remote;

@Remote
public interface AuthenticationBeanRemote {

	User checkCredentials(String username, String hash) 
			throws NoSuchUserException;

	void register(String email, String password) throws NotUniqueException;

	boolean checkConfirmCode(String token);

	void completeRegistration(String token, User user) 
			throws NoSuchUserException;
}
