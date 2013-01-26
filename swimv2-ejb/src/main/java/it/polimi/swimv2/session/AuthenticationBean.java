package it.polimi.swimv2.session;

import java.util.List;

import it.polimi.swimv2.entity.PendingToken;
import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.enums.TokenType;
import it.polimi.swimv2.session.exceptions.EmailException;
import it.polimi.swimv2.session.exceptions.NoSuchUserException;
import it.polimi.swimv2.session.exceptions.NotUniqueException;
import it.polimi.swimv2.session.remote.AuthenticationBeanRemote;
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
	public User checkCredentials(String username, String password) throws NoSuchUserException {
		User u = fetchUser(username);
		if(hasher.compareWithHash(password, u.getPasswordHash())) { 
			return u;
		} else {
			throw new NoSuchUserException();
		}
	}
	
	@Override
	public void register(String email, String password, String uri) throws NotUniqueException, EmailException {
		if(userExists(email)) {
			throw new NotUniqueException();
		}
		String token = createToken(email, password, TokenType.NEWUSER);
		try {
			emailer.sendConfirmationEmail(email,  token, uri);
		} catch(MessagingException me) {
			throw new EmailException(me);
		}
	}
	
	@Override
	public void requestPasswordReset(String email, String uri) throws EmailException, NoSuchUserException {
		try {
			fetchUser(email);
			String token = createToken(email, null, TokenType.RESETPASSWORD);
			emailer.sendResetEmail(email, token, uri);
		} catch(MessagingException me) {
			throw new EmailException(me);
		}
	}
	
	@Override
	public TokenType checkConfirmCode(String token) {
		try {
			PendingToken obj = fetchToken(token);
			return obj.getType();
		} catch(NoSuchUserException e) {
			return TokenType.INVALID;
		}
	}
	
	@Override
	public User resetPassword(String password, String token) throws NoSuchUserException{
		PendingToken obj = null;
		obj = fetchToken(token);
		if(obj.getType() != TokenType.RESETPASSWORD) {
			throw new NoSuchUserException();
		}
		User u = fetchUser(obj.getEmail());
		u.setPasswordHash(hasher.hash(password));
		return manager.merge(u);
	}
	
	@Override
	public User completeRegistration(String token, User user) throws NoSuchUserException {
		PendingToken pt = fetchToken(token);
		if(pt.getType() != TokenType.NEWUSER) {
			throw new NoSuchUserException();
		}
		user.setEmail(pt.getEmail());
		user.setPasswordHash(pt.getPasswordHash());
		manager.persist(user);
		manager.remove(pt);
		return user;
	}
	
	
	private String createToken(String email, String password, TokenType type) {
		String token = rsg.nextString();
		PendingToken tokenObject = manager.find(PendingToken.class, email);
		if(tokenObject == null) {
			tokenObject = new PendingToken(email, token, type);
			// FIXME check token uniqueness, otherwise generate another one!!!
			manager.persist(tokenObject);
		}
		if(password != null) {
			tokenObject.setPasswordHash(hasher.hash(password));
			manager.merge(tokenObject);
		}
		return tokenObject.getConfirmCode();
	}
	
	private User fetchUser(String email) throws NoSuchUserException {
		Query q = manager.createNamedQuery("User.findByEmail");
		q.setParameter("email", email);
		try {
			return (User) q.getSingleResult();
		} catch(NoResultException e) {
			throw new NoSuchUserException(e);
		}
	}
	
	private PendingToken fetchToken(String token) throws NoSuchUserException {
		Query q = manager.createNamedQuery("PendingToken.findByToken");
		q.setParameter("token", token);
		try {
			return (PendingToken) q.getSingleResult();
		} catch(NoResultException nre) {
			throw new NoSuchUserException(nre);
		}
	}
	
	private boolean userExists(String email) {
		Query q = manager.createNamedQuery("User.findByEmail");
		q.setParameter("email", email);
		@SuppressWarnings("unchecked")
		List<User> l = q.getResultList();
		return l != null && l.size() != 0;
	}

}