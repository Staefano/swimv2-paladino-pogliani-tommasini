package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.AbilityRequest;

import java.sql.Timestamp;
import java.util.List;

import it.polimi.swimv2.entity.HelpRequest;
import it.polimi.swimv2.entity.Notification;
import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.enums.NotificationType;
import it.polimi.swimv2.session.exceptions.NoFriendshipRequestException;
import it.polimi.swimv2.session.remote.NotificationBeanRemote;

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
	private EntityManager manager;

	public NotificationBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Notification notifyFriendshipRequest(User asker, User receiver, NotificationType type) throws NoFriendshipRequestException {
		//TODO manca controllo is pending is friend
		if(!(isPending(asker, receiver) || isFriend(asker, receiver))){
			Notification n = new Notification();
			n.setType(type);
			n.setSrcUser(asker);
			n.setTgtuser(receiver);
			// TODO timestamp
			manager.persist(n);
		return n;
		}
		
		throw new NoFriendshipRequestException();
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
		if(request.getType().equals(NotificationType.FRIENDSHIP_RECEIVED)) {
			n.setType(NotificationType.FRIENDSHIP_ACCEPTED);
		}
		if(request.getType().equals(NotificationType.FRIENDSHIP_RECEIVED_DIRECT)) {
			n.setType(NotificationType.FRIENDSHIP_ACCEPTED_DIRECT);
		}
		
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
	public void notifyAbilityAccepted(AbilityRequest request) {
		Notification n = new Notification();
		n.setType(NotificationType.ABILITY_ACCCEPTED);
		n.setAbility(request.getAbility());
		n.setTgtuser(request.getSender());
		n.setTimestamp(new Timestamp(System.currentTimeMillis()));
		manager.persist(n);
	}

	@Override
	public void notifyAbilityRejected(AbilityRequest request) {
		Notification n = new Notification();
		n.setType(NotificationType.ABILITY_REJECTED);
		n.setAbility(request.getAbility());
		n.setTgtuser(request.getSender());
		n.setTimestamp(new Timestamp(System.currentTimeMillis()));
		manager.persist(n);
	}
	
	@Override
	public void notifyAbilityChoice(AbilityRequest request, String choice) {
		List<AbilityRequest> reqs = getWithSameName(request);
		for (AbilityRequest r : reqs) {
			if (choice.equals("approve")) {
				notifyAbilityAccepted(r);
			} else {
				notifyAbilityRejected(r);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private List<AbilityRequest> getWithSameName(AbilityRequest request) {
		Query q = manager.createNamedQuery("AbilityRequest.findByName");
		q.setParameter("name", request.getAbility());
		return q.getResultList();
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



	@Override
	public void notifyAdminPromotion(User user) {
		Notification n = new Notification();
		n.setType(NotificationType.ADMIN_PROMOTION);
		n.setTgtuser(user);
		n.setTimestamp(new Timestamp(System.currentTimeMillis()));
		manager.persist(n);
	}

	@Override
	public void notifyRefusedHelpRe(HelpRequest hr) {
		Notification n = new Notification();
		n.setType(NotificationType.HELP_REJECTED);
		n.setTgtuser(hr.getSender());
		n.setSrcUser(hr.getReceiver());
		n.setTimestamp(new Timestamp(System.currentTimeMillis()));
		manager.persist(n);		
	}

	@Override
	public boolean isFriend(User asker, User receiver) {
		Query q = manager.createNamedQuery("Friendship.isFriend");
		q.setParameter("user1", asker);
		q.setParameter("user2", receiver);

		try {
			return (q.getSingleResult() != null);
		} catch (NoResultException nre) {
			return false;
		}
	}
	
	@Override
	public boolean isPending(User user1, User user2) {
		Query q = manager.createNamedQuery("Notification.isPending");
		q.setParameter("user1", user1);
		q.setParameter("user2", user2);
		q.setParameter("direct", NotificationType.FRIENDSHIP_RECEIVED_DIRECT);
		q.setParameter("indirect", NotificationType.FRIENDSHIP_RECEIVED);
		int count = ((Long) q.getSingleResult()).intValue();

		return count >0;
	}

}
