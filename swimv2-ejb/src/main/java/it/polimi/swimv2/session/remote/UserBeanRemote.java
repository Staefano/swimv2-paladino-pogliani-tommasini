package it.polimi.swimv2.session.remote;

import java.util.List;

import it.polimi.swimv2.entity.HelpRequest;
import it.polimi.swimv2.entity.Notification;
import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.entity.Feedback;
import it.polimi.swimv2.session.exceptions.NoSuchUserException;

import javax.ejb.Remote;

@Remote
public interface UserBeanRemote {

	/**
	 * return the user who has the corrisponding id
	 * 
	 * @throws NoSuchUserException
	 *             the exception is thows in case the user can't be found in the
	 *             database
	 */
	User getUserByID(int id) throws NoSuchUserException;

	/**
	 * return the list of the Feedback that the user u has received as HELPER
	 * 
	 * @throws NoSuchUserException
	 *             the exception is throws in case the user can't be found in
	 *             the database
	 */
	List<Feedback> getHelperFeedbacks(User u) throws NoSuchUserException;

	/**
	 * return the list of the Feedback that the user u has received as ASKER
	 * 
	 * @throws NoSuchUserException
	 *             the exception is throws in case the user can't be found in
	 *             the database
	 */
	List<Feedback> getAskerFeedbacks(User u) throws NoSuchUserException;

	/**
	 * return the list of the notification received by the user u
	 * 
	 * @throws NoSuchUserException
	 *             the exception is throws in case the user can't be found in
	 *             the database
	 */
	List<Notification> getNotifications(User u) throws NoSuchUserException;

	/**
	 * return the list of the opened help request that concern the user u as
	 * helper or asker
	 * 
	 * @throws NoSuchUserException
	 *             the exception is throws in case the user can't be found in
	 *             the database
	 */
	List<HelpRequest> getOpenedHelpRequest(User u) throws NoSuchUserException;

	/**
	 * Search for users in the database
	 * 
	 * @param queryString
	 *            a string that is the name plus surname or part of it
	 * @param page
	 *            the number of the result page being returned (that is, are
	 *            returned (at most) pageSize results from the page * pageSize.
	 * @param pageSize
	 *            the maximum number of results to be returned
	 */
	List<User> searchUser(String queryString, int page, int pageSize);

	/**
	 * return the list of closed helprequest that concern user u as asker or helper
	 * @throws NoSuchUserException  the exception is throws in case the user can't be found in the
	 *             database
	 */
	List<HelpRequest> getClosedHelpRequest(User u) throws NoSuchUserException;

	/**
	 * return the list of the helprequest that concern user u as helper
	 * @throws NoSuchUserException  the exception is throws in case the user can't be found in the
	 *             database
	 */
	List<HelpRequest> getProvidedHelpRequest(User u) throws NoSuchUserException;

	/**
	 * return the list of the helprequest that concern user u as Asker
	 * @throws NoSuchUserException  the exception is throws in case the user can't be found in the
	 *             database
	 */
	List<HelpRequest> getReceivedHelpRequest(User u) throws NoSuchUserException;

	/**
	 * return the modified user with some new abilities
	 */
	User addUserAbility(User user, String chosenAb);

	/**
	 * @return the updated user u
	 * @throws NoSuchUserException
	 *             the exception is throws in case the user can't be found in
	 *             the database
	 */
	User editProfile(int userId, String name, String surname, String website,
			String birthdate, String location, String minibio,
			String description) throws NoSuchUserException;

	/**
	 * return a modified user with no chosendAb in his set
	 */
	User removeUserAbility(User user, String chosenAb);

	/**
	 * Retrieves the number of users satisfying the specified search criteria
	 * 
	 * @param queryString
	 *            a string that is the name plus surname or part of it
	 */
	long countSearchUser(String queryString);

	/**
	 * prmote user as administrator
	 */
	void promoteAdmin(User user);
}
