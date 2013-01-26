package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.Message;
import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.exceptions.OperationFailedException;
import it.polimi.swimv2.session.remote.FriendShipBeanRemote;
import it.polimi.swimv2.session.remote.MessageManagerBeanRemote;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.EJB;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class MessageManagerBean implements MessageManagerBeanRemote {

	@PersistenceContext(unitName="swimv2")
	private EntityManager manager;
	
	@EJB
	private FriendShipBeanRemote friendshipBean;
	
	public void write(User from, User to, String text) throws OperationFailedException {
		if(!friendshipBean.isFriend(from,  to)) {
			throw new OperationFailedException("sender and receiver are not friends!");
		}
		Message m = new Message(from, to, text);
		manager.persist(m);
	}
	
	@Override @SuppressWarnings("unchecked")
	public List<Message> getConversation(User current, User other) {
		Query q = manager.createNamedQuery("Message.findConversation");
		q.setParameter("user1", current);
		q.setParameter("user2", other);
		List<Message> ret = q.getResultList();
		markAllAsRead(ret, current);
		return ret;
	}
	
	@Override @SuppressWarnings("unchecked")
	public List<User> getUsersWithConversations(User current) {
		Query q1 = manager.createNamedQuery("Message.findUsersWithSentMessages");
		q1.setParameter("user", current);
		Query q2 = manager.createNamedQuery("Message.findUsersWithReceivedMessages");
		q2.setParameter("user", current);
		List<User> l1 = q1.getResultList();
		List<User> l2 = q2.getResultList();
		for(User u : l2) {
			if(!l1.contains(u)) {
				l1.add(u);
			}
		}
		return l1;
	}
	
	@Override @SuppressWarnings("unchecked")
	public List<User> getUnreadConversations(User current) {
		Query q = manager.createNamedQuery("Message.findUnreadConversations");
		q.setParameter("user", current);
		return (List<User>) q.getResultList();
	}
	
	@Override
	public void deleteConversation(User current, User other) {
		/* delete from the POV of the sender */
		String qs1 = "UPDATE Message m SET m.senderDeleted = true WHERE m.sender = :current AND m.receiver = :other";
		/* delete from the POV of the receiver */
		String qs2 = "UPDATE Message m SET m.receiverDeleted = true WHERE m.sender = :other AND m.receiver = :current AND m.msgRead = true";
		/* clean up task (may be periodical in future releases for performance) */
		String qs3 = "DELETE FROM Message m WHERE m.senderDeleted = true AND m.receiverDeleted = true";
		Query q1 = manager.createQuery(qs1).setParameter("current",  current).setParameter("other",  other);
		q1.executeUpdate();
		Query q2 = manager.createQuery(qs2).setParameter("current", current).setParameter("other", other);
		q2.executeUpdate();
		Query q3 = manager.createQuery(qs3);
		q3.executeUpdate();
		
	}
	
	private void markAllAsRead(List<Message> ret, User current) {
		for(Message msg : ret) {
			if(!msg.isMsgRead() && msg.getReceiver().equals(current)) {
				msg.setMsgRead(true);
				manager.merge(msg);
			}
		}
	}
}
