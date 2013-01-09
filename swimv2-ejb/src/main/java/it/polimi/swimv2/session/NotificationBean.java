package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.AbilityRequest;

import java.util.List;
import it.polimi.swimv2.entity.Notification;
import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.enums.NotificationType;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@Local(NotificationBeanRemote.class)
public class NotificationBean implements NotificationBeanRemote {

	@PersistenceContext(unitName = "swimv2")
	EntityManager manager;

	public NotificationBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Notification notifyFriendshipRequest(User asker, User receiver) {

		Notification n = new Notification();
		n.setType(NotificationType.FRIENDSHIP_RECEIVED);
		n.setSrcUser(asker);
		n.setTgtuser(receiver);
		// TODO timestamp
		manager.persist(n);
		return n;

	}

	@Override
	public Notification notifyFriendshipAccepted(User replier,
			String notificationID) {

		Notification n = new Notification();
		Notification request = getByID(notificationID);
		// quello che aveva chiesto l'amicizia
		n.setTgtuser(request.getSrcUser());
		// quello che sta rispondendo
		n.setSrcUser(replier);
		n.setType(NotificationType.FRIENDSHIP_ACCEPTED);
		// metto la nuova
		manager.persist(n);
		// tolgo la vecchia
		manager.remove(request);
		return n;

	}

	@Override
	public Notification notifyAbilityRequest(User asker) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notification notifyAbilityAccepted(AbilityRequest request) {
		
		Notification n = new Notification();
		n.setType(NotificationType.ABILITY_ACCCEPTED);
		n.setAbility(request.getAbility());
		n.setTgtuser(request.getSender());
		//TODO n.setTimestamp(timestamp);
		manager.persist(n);
		return null;
	}

	@Override
	public List<Notification> getNotifications(User u) {
		Query q = manager.createNamedQuery("Notification.findBytgtUser");
		q.setParameter("user", u);
		try {
			@SuppressWarnings("unchecked")
			List<Notification> notifications = (List<Notification>) q
					.getResultList();
			return notifications;

		} catch (NoResultException nre) {
			// TODO
			return null;
		}

	}

	@Override
	public Notification getByID(String id) {

		Query q = manager.createNamedQuery("Notification.findByID");
		q.setParameter("id", Integer.parseInt(id));
		try {
			return (Notification) q.getSingleResult();
		} catch (NoResultException nre) {
			// TODO da sistemare
			return null;
		}
	}

	@Override
	public void deleteNotification(String notificationId) {
		Query q = manager.createNamedQuery("Notification.findByID");
		q.setParameter("id", Integer.parseInt(notificationId));

		try {

			manager.remove((Notification) q.getSingleResult());

		} catch (NoResultException nre) {
			// TODO da sistemare
		}
	}

}
