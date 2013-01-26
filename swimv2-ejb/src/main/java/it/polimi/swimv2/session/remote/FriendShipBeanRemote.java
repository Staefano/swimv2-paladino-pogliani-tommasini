package it.polimi.swimv2.session.remote;

import it.polimi.swimv2.entity.User;

import java.util.List;

public interface FriendShipBeanRemote {
	
	/**
	 * @return the list of User u's friends (User)
	 */
	List<User> getFriends(User u);
	
	/**
	 * @param notificationSrc 
	 * @param receiver the user who received the friendrequest
	 */
	void createFriendship(String notificationSrc, User receiver);

	/**
	 * is true if user asker and receiver are friends 
	  */
	boolean isFriend(User asker, User receiver);

	/**
	 * is true if is possibile to ask a friendship by the logged user to user u 
	 */
	boolean isRequestAllowed(User loggedUser, User u);
	
	/**
	 * is true if exist a friendship of tipe DIRECT between u1 and u2
	 */
	boolean isDirect(User u1, User u2);

}
