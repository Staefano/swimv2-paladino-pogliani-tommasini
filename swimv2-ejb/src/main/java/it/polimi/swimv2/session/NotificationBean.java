package it.polimi.swimv2.session;

import java.util.List;

import it.polimi.swimv2.entity.Notification;
import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.enums.NotificationType;

import javax.ejb.CreateException;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
		n.setSrcUser(asker);
		n.setTgtuser(receiver);
		//TODO timestamp
		manager.persist(n);
		return n;

	}

	@Override
	public Notification notifyFriendshipAccepted(User replier, Notification request) {
		
		Notification n = new Notification();
		//quello che aveva chiesto l'amicizia
		n.setTgtuser(request.getSrcUser());
		//quello che sta rispondendo
		n.setSrcUser(replier);
		n.setType(NotificationType.FRIENDSHIP_ACCEPTED);
		//metto la nuova
		manager.persist(n);
		//tolgo la vecchia
		manager.remove(request);
		return n;
	
	
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

	@Override
	public List<Notification> getNotifications(String u) {
		Query q = manager.createNamedQuery("Notification.findBytgtUser");
		q.setParameter("user", Integer.parseInt(u));
		try{
			@SuppressWarnings("unchecked")
			List<Notification> notifications = (List<Notification>) q.getResultList();
			return notifications;
		
		}catch(NoResultException nre){
			//TODO
			return null;
		}
		
	}
}
