package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.Notification;
import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.enums.NotificationType;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class NotificationBean
 */
@Stateless
@Local(NotificationBeanRemote.class)
public class NotificationBean implements NotificationBeanRemote {

	@PersistenceContext(unitName = "swimv2")
	EntityManager manager;

	/**
	 * Default constructor.
	 */
	public NotificationBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Notification notifyFriendshipRequest(User asker, User receiver) {

		Notification n = new Notification();
		n.setType(NotificationType.FRIENDSHIP_RECEIVED);
		n.setUser1(asker);
		n.setUser2(receiver);
		//TODO timestamp
		manager.persist(n);
		return n;

	}

	@Override
	public Notification notifyFriendshipAccepted(User asker, User receiver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notification notifyAbilityRequest(User asker) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notification notifyAbilityAccepted(User asker) {
		// TODO Auto-generated method stub
		return null;
	}
}
