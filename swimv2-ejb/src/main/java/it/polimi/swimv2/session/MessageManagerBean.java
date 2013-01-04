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
	
	// all the messages or with a limitation???
	@SuppressWarnings("unchecked")
	public List<Message> getByUsername(User from, User to) {
		final String Q = "from Message m " +
				"where m.sender = :from and m.receiver = :to " +
				"order by m.timestamp desc";
		Query q = manager.createQuery(Q);
		q.setParameter("from",  from);
		q.setParameter("to", to);
		return (List<Message>) q.getResultList();
	}
	
	// TODO non teniamo traccia degli unread!!!
	public List<Message> getLatestUnread(User to) {
		return null;
	}

}
