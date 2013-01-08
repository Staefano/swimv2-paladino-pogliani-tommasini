package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.Message;
import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.exceptions.OperationFailedException;

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
	
	// TODO support for pagination???
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
		Query q = manager.createNamedQuery("Message.findUsersWithConversations");
		q.setParameter("user", current);
		return (List<User>) q.getResultList();
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
