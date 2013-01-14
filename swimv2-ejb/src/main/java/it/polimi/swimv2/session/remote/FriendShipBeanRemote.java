package it.polimi.swimv2.session.remote;

import it.polimi.swimv2.entity.User;

import java.util.List;

public interface FriendShipBeanRemote {
	
	void createFriendship(String notificationSrc, User receiver);
	
	List<User> getFriends(User u);

	boolean isFriend(User asker, User receiver);

	boolean isRequestAllowed(User loggedUser, User u);
	
	boolean isDirect(User u1, User u2);

}
