package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.AbilityRequest;
import it.polimi.swimv2.entity.Notification;
import it.polimi.swimv2.entity.User;

public interface NotificationBeanRemote {

	

	public Notification notifyFriendshipRequest(User asker, User receiver);
	
	public Notification notifyFriendshipAccepted(User asker, User receiver);

	public Notification notifyAbilityRequest(User asker);

	public Notification notifyAbilityAccepted(AbilityRequest request);

}
