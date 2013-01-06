package it.polimi.swimv2.session;

import java.util.List;

import it.polimi.swimv2.entity.HelpRequest;
import it.polimi.swimv2.entity.Notification;
import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.entity.Feedback;
import it.polimi.swimv2.session.exceptions.NoSuchUserException;

import javax.ejb.Remote;

@Remote
public interface UserBeanRemote {
	
	User getUserByID(int id) throws NoSuchUserException;

	void editProfile(User u) throws NoSuchUserException;

	List<Feedback> getHelperFeedbacks(User u) throws NoSuchUserException;

	List<Feedback> getAskerFeedbacks(User u) throws NoSuchUserException;

	List<Notification> getNotifications(User u) throws NoSuchUserException;;

	List<HelpRequest> getOpenedHelpRequest(User u) throws NoSuchUserException;;

	List<HelpRequest> getClosedHelpRequest(User u) throws NoSuchUserException;;
}
