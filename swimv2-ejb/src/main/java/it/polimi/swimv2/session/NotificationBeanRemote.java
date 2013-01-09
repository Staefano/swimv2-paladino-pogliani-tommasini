package it.polimi.swimv2.session;


import it.polimi.swimv2.entity.AbilityRequest;

import java.util.List;
import it.polimi.swimv2.entity.Notification;
import it.polimi.swimv2.entity.User;

public interface NotificationBeanRemote {

	

	 Notification notifyFriendshipRequest(User asker, User receiver);
	
	 Notification notifyFriendshipAccepted(User replier, Notification request);

	 Notification notifyAbilityRequest(User asker);

	 Notification notifyAbilityAccepted(AbilityRequest request);
	 
	 List<Notification> getNotifications(String u);

}
