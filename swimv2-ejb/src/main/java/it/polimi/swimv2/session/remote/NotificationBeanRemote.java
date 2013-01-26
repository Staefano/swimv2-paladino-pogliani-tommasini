package it.polimi.swimv2.session.remote;

import it.polimi.swimv2.entity.AbilityRequest;

import java.util.List;

import it.polimi.swimv2.entity.HelpRequest;
import it.polimi.swimv2.entity.Notification;
import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.enums.NotificationType;
import it.polimi.swimv2.session.exceptions.OperationFailedException;

public interface NotificationBeanRemote {

	/**
	 * @return the list of the notifications received by u
	 */
	List<Notification> getNotifications(User u);

	/**
	 * @return the notification with the provided id
	 */
	Notification getByID(String id);

	/**
	 * @param replier user who receive the notification
	 * @return the Notification created 
	 */
	Notification notifyFriendshipAccepted(User replier, String notificationID);

	/**
	 * notifiy a friendrequest from asker to the receiver
	 * @return the notification to provide
	 * @throws OperationFailedException
	 */
	Notification notifyFriendshipRequest(User asker, User receiver, NotificationType type) 
			throws OperationFailedException;
	
	/**
	 * is true if exist a pending frindrequest bewtween user1 and user2
	 */
	boolean isPending(User user1, User user2);

	/**
	 * is true if user1 and user2 are friends
	 */
	boolean isFriend(User user1, User user2);

	/**
	 * delete the notification with provided id 
	 */
	void deleteNotification(String notificationID);

	/**
	 * notify to the users who asked for the ability that it was accepted
	 * 	 */
	void notifyAbilityAccepted(AbilityRequest request);

	/**
	 * notify to the users who asked for the ability that it was refused
	 */
	void notifyAbilityRejected(AbilityRequest request);
	
	void notifyAdminPromotion(User user);

	void notifyRefusedHelpRe(HelpRequest hr);

	void notifyAbilityChoice(AbilityRequest request, String choice);

}
