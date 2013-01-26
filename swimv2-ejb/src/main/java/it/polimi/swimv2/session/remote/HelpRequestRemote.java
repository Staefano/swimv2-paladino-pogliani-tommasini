package it.polimi.swimv2.session.remote;

import java.util.List;

import it.polimi.swimv2.entity.Ability;
import it.polimi.swimv2.entity.Comment;
import it.polimi.swimv2.entity.HelpRequest;
import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.enums.FeedbackValue;
import it.polimi.swimv2.session.exceptions.ClosedHelpRequestException;
import it.polimi.swimv2.session.exceptions.NoSouchHRException;

public interface HelpRequestRemote {

	/**
	 * @param sender
	 *            user who ask for help
	 * @param receiver
	 *            user who is asked for providing help
	 * @param subject
	 *            title of help request
	 * @param abilties
	 *            the set of ablities the receiver is asked to provide
	 * @return a new helprequest between asker and receiver
	 */
	HelpRequest askForHelp(User sender, User receiver, String subject,
			List<Ability> abilties);

	/**
	 * @param hr
	 *            helprequest
	 * @param comment
	 * @param sender
	 *            , the sender of the comment
	 * @throws ClosedHelpRequestException
	 */
	void addComment(HelpRequest hr, String comment, User sender)
			throws ClosedHelpRequestException;

	/**
	 * @param request
	 *            , the helprequest
	 * @param evaluation
	 *            , evaluation of the performance in the helprequest, -1, 0, +1
	 * @param comment
	 *            , comment about the feedback
	 * @param user
	 *            , user who is giving the feedback
	 * @throws ClosedHelpRequestException
	 */
	void addFeedback(HelpRequest request, FeedbackValue evaluation,
			String comment, User user) throws ClosedHelpRequestException;

	/**
	 * @param hr
	 * @throws NoSouchHRException
	 */
	void refuseHR(HelpRequest hr) throws NoSouchHRException;

	/**
	 * @param hr
	 * @throws NoSouchHRException
	 */
	void acceptHR(HelpRequest hr) throws NoSouchHRException;

	/**
	 * @param id
	 *            of the helprequest i'm looking for
	 * @return
	 * @throws NoSouchHRException
	 */
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
	 * return the list of the help request the user u is receiving by other
	 * users
	 */
	List<HelpRequest> getOpenRequestedHR(User u);

	/**
	 * return the list of the help request received by the user u all the help
	 * other user have given to him
	 */
	List<HelpRequest> getClosedRequestedHR(User u);

	/**
	 * return the list of comments in the helprequest
	 * 
	 */
	List<Comment> getComments(HelpRequest hr);

}
