package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.User;

import java.util.List;

public interface FriendShipBeanRemote {
	
	
	//public List<Friendship> getFriendship(User u);
	
	void createFriendship(User asker, User receiver);
	
	boolean isFriend(User asker, User receiver);

	List<User> getFriends(User u);
	
	

}
