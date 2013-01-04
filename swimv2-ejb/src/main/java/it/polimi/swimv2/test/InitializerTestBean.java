package it.polimi.swimv2.test;

import java.util.List;

import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.utils.HashService;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * DEBUG. This class is used for testing and debugging purpose. It allows 
 * insertions and deletions on the database that are otherwise impossible, for the
 * sake of testing EJB classes during early development phases.
 */
@Stateless
public class InitializerTestBean<Message> implements InitializerTestBeanRemote {
	
	@PersistenceContext(unitName="swimv2") 
	private EntityManager em;
	
	User[] predef = new User[4];
	
	@PostConstruct
	public void loadUsers() {
		HashService h = new HashService();
		predef[0] = new User();
		predef[0].setName("Mickey");
		predef[0].setSurname("Mouse");
		predef[0].setEmail("mickey.mouse@example.com");
		predef[0].setPasswordHash(h.hash("topolino"));
		
		predef[1] = new User();
		predef[1].setName("Donald");
		predef[1].setSurname("Duck");
		predef[1].setEmail("donald.duck@example.com");
		predef[1].setPasswordHash(h.hash("paperino"));
		
		predef[2] = new User();
		predef[2].setName("Goofy");
		predef[2].setSurname("Goof");
		predef[2].setEmail("goofy.goof@example.com");
		predef[2].setPasswordHash(h.hash("pippo"));
		
		predef[3] = new User();
		predef[3].setName("Scrooge");
		predef[3].setSurname("McDuck");
		predef[3].setEmail("scrooge.mcduck@example.com");
		predef[3].setPasswordHash(h.hash("paperondepaperoni"));
	}
	
	public void deletePredefinedUsers() {
		for (User u : predef) {
			// GRRRR
			@SuppressWarnings("unchecked")
			List<Message> lom = (List<Message>) em
					.createQuery(
							"SELECT m FROM Message m "
									+ "WHERE m.sender.email LIKE :email OR m.receiver.email LIKE :email")
					.setParameter("email", u.getEmail()).getResultList();
			for (Message m : lom) {
				em.remove(m);
			}
		}
		for(User u : predef) {
			em.createQuery("DELETE from User u WHERE u.email LIKE :email")
			.setParameter("email", u.getEmail()).executeUpdate();
		}
	}

	public void createPredefinedUsers() {
		for(User u : predef)
			em.merge(u);
	}
	
}
