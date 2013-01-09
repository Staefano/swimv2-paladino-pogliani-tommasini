package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.AbilityRequest;

import java.util.List;
import it.polimi.swimv2.entity.Notification;
import it.polimi.swimv2.entity.User;

public interface NotificationBeanRemote {

	Notification getByID(String id);

	void deleteNotification(String notificationID);
	
	List<Notification> getNotifications(User u);

	Notification notifyFriendshipAccepted(User replier, String notificationID);

	Notification notifyFriendshipRequest(User asker, User receiver);

	Notification notifyAbilityRequest(User asker);

	Notification notifyAbilityAccepted(AbilityRequest request);


}
