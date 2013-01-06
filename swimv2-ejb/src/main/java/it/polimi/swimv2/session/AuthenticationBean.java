package it.polimi.swimv2.session;

import java.util.List;

import it.polimi.swimv2.entity.PendingUser;
import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.exceptions.NoSuchUserException;
import it.polimi.swimv2.session.exceptions.NotUniqueException;
import it.polimi.swimv2.session.utils.HashService;
import it.polimi.swimv2.session.utils.RandomStringGenerator;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class AuthenticationBean implements AuthenticationBeanRemote {
	
	@EJB 
	private EmailServiceLocal emailer;
	
	@PersistenceContext(unitName="swimv2")
	private EntityManager manager;
	
	private static final int TOKEN_LENGTH = 50;
	
	private HashService hasher;
	private RandomStringGenerator rsg;
	
	@PostConstruct
	private void setup() {
		hasher = new HashService();
		rsg = new RandomStringGenerator(TOKEN_LENGTH);
	}
	
	@Override
	public User checkCredentials(String username, String password) 
			throws NoSuchUserException {
		Query q = manager.createNamedQuery("User.findByEmail");
		q.setParameter("email", username);
		try {
			User u = (User) q.getSingleResult();
			if(hasher.compareWithHash(password, u.getPasswordHash())) { 
				return u;
			} else {
				throw new NoSuchUserException();
			}
		} catch(NoResultException e) {
			throw new NoSuchUserException();
		}
	}
	
	@Override
	public void register(String email, String password) throws NotUniqueException {
		if(userExists(email)) {
			throw new NotUniqueException();
		}

		String token = rsg.nextString();
		PendingUser existing = manager.find(PendingUser.class, email);
		if(existing == null) {
			PendingUser pending = new PendingUser(email, hasher.hash(password), token);
			// TODO check token uniqueness, otherwise generate another one!!! FIXME
			manager.persist(pending);
		} else {
			existing.setPasswordHash(hasher.hash(password));
			token = existing.getConfirmCode();
			manager.merge(existing);
		}

		try {
			emailer.sendConfirmationEmail(email,  token);
		} catch(MessagingException me) {
			throw new RuntimeException("Cannot send the email! " + me.getMessage());
		}
	}
	
	@Override
	public boolean checkConfirmCode(String token) {
		Query q = manager.createNamedQuery("PendingUser.findByToken");
		q.setParameter("token", token);
		try {
			q.getSingleResult();
			return true;
		} catch(NoResultException e) {
			return false;
		}
	}
	
	@Override
	public User completeRegistration(String token, User user) 
			throws NoSuchUserException {
		Query q = manager.createNamedQuery("PendingUser.findByToken");
		q.setParameter("token", token);
		try {
			PendingUser pu = (PendingUser) q.getSingleResult();
			user.setEmail(pu.getEmail());
			user.setPasswordHash(pu.getPasswordHash());
			manager.persist(user);
			manager.remove(pu);
			return user;
		} catch(NoResultException e) {
			throw new NoSuchUserException(); // TODO sicuri che e' solo perche' non c'e' l'utente???
		}
	}
	
	private boolean userExists(String email) {
		Query q = manager.createNamedQuery("User.findByEmail");
		q.setParameter("email", email);
		@SuppressWarnings("unchecked")
		List<User> l = q.getResultList();
		if( l !=null && l.size() != 0) {
			return true;
		} else {
			return false;
		}
	}

}