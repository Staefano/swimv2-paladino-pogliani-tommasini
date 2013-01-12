package it.polimi.swimv2.session.remote;

import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.enums.TokenType;
import it.polimi.swimv2.session.exceptions.NoSuchUserException;
import it.polimi.swimv2.session.exceptions.NotUniqueException;

import javax.ejb.Remote;

@Remote
public interface AuthenticationBeanRemote {

	/**
	 * Checks whether the user can or not log in to the system with the provided credentials
	 * @param username The email address of the user registered in the system
	 * @param password The (cleartext) password of the user
	 * @return an User object describing the user whom credentials were provided
	 * @throws NoSuchUserException if the user doesn't exist in the system or the password is wrong
	 */
	User checkCredentials(String username, String password) throws NoSuchUserException;

	/**
	 * Register a new user in the system, and sends an email to the provided address
	 * with the token needed to complete the registration process. If the user already requested
	 * a token to be sent via email, the same token will be sent again to the same email address.
	 * @param email the email of the user
	 * @param password the password of the user
	 * @throws NotUniqueException if there is another user registered into the system with the same email
	 */
	void register(String email, String password, String uri) throws NotUniqueException;

	/**
	 * Checks whether the token is valid
	 * @param token the token to be verified
	 * @return the type of the PendingToken entry corresponding to the token, or 
	 * TokenType.INVALID if there is no entry corresponding to that token
	 */
	TokenType checkConfirmCode(String token);

	/** 
	 * Completes the registration by merging the User coming from the client with data saved along with the token
	 * @param token the token sent by email to the user to complete the registration
	 * @param user a new User object containing the personal data of the user
	 * @return the complete User obtained by merging the data provided as parameter with that generated
	 * by the server (the user ID) and already saved into the database (username and password)
	 * @throws NoSuchUserException if the registration cannot be completed because the token is not valid
	 */
	User completeRegistration(String token, User user) throws NoSuchUserException;

	/**
	 * Change the password of the user
	 * @param password the new password (cleartext)
	 * @param token a valid TokenType.RESETPASSWORD token
	 * @return the User object with the changed password
	 * @throws NoSuchUserException if the token is not valid
	 */
	User resetPassword(String password, String token) throws NoSuchUserException;

	/**
	 * Require a password reset, by storing the useful data inside the database and sending the 
	 * email containing the registration link
	 * @param email the email of the user who wants to reset his\her password
	 */
	void requestPasswordReset(String email, String uri);
}
