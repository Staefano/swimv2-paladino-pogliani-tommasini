package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.Message;
import it.polimi.swimv2.entity.User;

import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class MessageManagerBean implements MessageManagerBeanRemote {

	@PersistenceContext(unitName="swimv2")
	private EntityManager manager;
	
	public void write(User from, User to, String text) {
		Message m = new Message(from, to, text);
		manager.persist(m);
	}
	
	// TODO support for pagination???
	@SuppressWarnings("unchecked")
	public List<Message> getConversation(User user1, User user2) {
		Query q = manager.createNamedQuery("Message.findConversation");
		q.setParameter("user1",  user1);
		q.setParameter("user2", user2);
		return (List<Message>) q.getResultList();
	}
	
	//TODO change to list of (User, LatestMessage, isUnread
	// for now returns a list of all the friends of the user! (boh...)
	public List<User> getConversationIndex() {
		return null;
	}
	
	// TODO non teniamo traccia degli unread!!!
	public List<Message> getLatestUnread(User to) {
		return null;
	}

}
