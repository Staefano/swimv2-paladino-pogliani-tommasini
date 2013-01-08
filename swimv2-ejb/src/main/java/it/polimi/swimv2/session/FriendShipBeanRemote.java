package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.Friendship;
import it.polimi.swimv2.entity.User;

import java.util.List;

public interface FriendShipBeanRemote {
	
	
	public List<Friendship> getFriendship(User u);
	
	public void createPendingFriendship(User asker, User receiver);
	
	public void confirmFriendship(Friendship f);
	
	public boolean isFriend(User asker, User receiver);
	
	

}
