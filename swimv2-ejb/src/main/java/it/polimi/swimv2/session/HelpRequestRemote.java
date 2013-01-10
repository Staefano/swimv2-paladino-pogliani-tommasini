package it.polimi.swimv2.session;

import java.util.List;

import it.polimi.swimv2.entity.Ability;
import it.polimi.swimv2.entity.Comment;
import it.polimi.swimv2.entity.HelpRequest;
import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.exceptions.ClosedHelpRequestException;
import it.polimi.swimv2.session.exceptions.NoSouchHRException;

public interface HelpRequestRemote {

	HelpRequest askForHelp(User sender, User receiver, String subject,
			List<Ability> abilties);

	List<Comment> getComments(HelpRequest hr);

	void giveFeedback(int value, String comment, HelpRequest hr, User user)
			throws ClosedHelpRequestException;

	void addComment(HelpRequest hr, String comment, User sender)
			throws ClosedHelpRequestException;;
			
	HelpRequest findByID(int id) throws NoSouchHRException;

	/**
	 * return the list of the help request the user u is giving to other users
	 */
	List<HelpRequest> getOpenProvidedHR(User u);

	/**
	 * return the list of the help request the user u has given to other users
	 */
	List<HelpRequest> getClosedProvidedHR(User u);

	/**
	 * return the list of the help request the user u is reiving by other users
	 */
	List<HelpRequest> getOpenReceivedHR(User u);

	/**
	 * return the list of the help request received by the user u all the help
	 * other user have given to him
	 */
	List<HelpRequest> getClosedReceiveddHR(User u);

}
