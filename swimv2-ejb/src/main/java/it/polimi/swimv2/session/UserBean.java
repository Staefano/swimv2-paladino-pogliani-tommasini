package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.Feedback;
import it.polimi.swimv2.entity.HelpRequest;
import it.polimi.swimv2.entity.Notification;
import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.entity.UserImage;
import it.polimi.swimv2.enums.RequestStatus;
import it.polimi.swimv2.session.exceptions.NoSuchUserException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UserBean implements UserBeanRemote {

	@PersistenceContext(unitName = "swimv2")
	private EntityManager manager;

	@Override
	public List<Feedback> getHelperFeedbacks(User u) throws NoSuchUserException {

		List<Feedback> feedbackList = new ArrayList<Feedback>();
		Query q = manager.createNamedQuery("HelpRequest.findByHelper");
		q.setParameter("helper", u);
		try {
			// TODO Brutto, da rivedere
			List<?> helpRequestList = q.getResultList();

			for (Object o : helpRequestList) {

				HelpRequest h = (HelpRequest) o;
				feedbackList.add(h.getReceiverFeedback());

			}

			return feedbackList;

		} catch (NoResultException nre) {
			throw new NoSuchUserException();
		}
	}

	@Override
	public List<Feedback> getAskerFeedbacks(User u) throws NoSuchUserException {

		List<Feedback> feedbackList = new ArrayList<Feedback>();
		Query q = manager.createNamedQuery("HelpRequest.findByAsker");
		q.setParameter("asker", u);
		try {

			List<?> helpRequestList = q.getResultList();

			for (Object o : helpRequestList) {

				HelpRequest h = (HelpRequest) o;
				feedbackList.add(h.getReceiverFeedback());

			}

			return feedbackList;

		} catch (NoResultException nre) {
			throw new NoSuchUserException();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Notification> getNotifications(User u)
			throws NoSuchUserException {
		Query q = manager.createNamedQuery("Notification.findBytgtUser");
		try {
			q.setParameter("user", u);
			return (List<Notification>) q.getResultList();
		} catch (NoResultException nre) {
			throw new NoSuchUserException();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HelpRequest> getOpenedHelpRequest(User u)
			throws NoSuchUserException {

		Query q = manager.createNamedQuery("Notification.findByStatus");
		try {
			q.setParameter("status", RequestStatus.WAITING);
			return (List<HelpRequest>) q.getResultList();

		} catch (NoResultException nre) {
			throw new NoSuchUserException();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HelpRequest> getClosedHelpRequest(User u)
			throws NoSuchUserException {

		Query q = manager.createNamedQuery("Notification.findByStatus");
		try {
			q.setParameter("status", RequestStatus.CLOSED);
			return (List<HelpRequest>) q.getResultList();

		} catch (NoResultException nre) {
			throw new NoSuchUserException();
		}
	}

	@Override
	public User getUserByID(int id) throws NoSuchUserException {
		Query q = manager.createNamedQuery("User.getUserByID");
		q.setParameter("id", id);
		try {
			return (User) q.getSingleResult();
		} catch (NoResultException nre) {
			throw new NoSuchUserException();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> searchUser(String queryString) {
		Query q = manager.createNamedQuery("User.searchUser");
		q.setParameter("name", '%' + queryString.toLowerCase().trim() + '%');
		return q.getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<HelpRequest> getProvidedHelpRequest(User u)
			throws NoSuchUserException {
		Query q = manager.createNamedQuery("HelpRequest.findByHelper");
		try {
			q.setParameter("helper", u);
			return (List<HelpRequest>) q.getResultList();

		} catch (NoResultException nre) {
			throw new NoSuchUserException();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<HelpRequest> getReceivedHelpRequest(User u)
			throws NoSuchUserException {
		Query q = manager.createNamedQuery("HelpRequest.findByAsker");
		try {
			q.setParameter("asker", u);
			return (List<HelpRequest>) q.getResultList();

		} catch (NoResultException nre) {
			throw new NoSuchUserException();
		}
	}
	
	@Override
	public void setImage(User user, byte[] img, String mimeType) {
		// todo controlli sull'immagine, ridimensionamento & co...
		UserImage image = new UserImage(mimeType, img);
		// remove the old image and substitute it with the new one
		User jpaUser = manager.find(User.class, user.getId());
		UserImage old = jpaUser.getImage();
		manager.persist(image);
		jpaUser.setImage(image);
		if(old != null) {
			manager.remove(old);
		}
		manager.merge(jpaUser);
	}

	@Override
	public User editProfile(User u, String name, String surname,
			String website, String birthdate, String location, String minibio,
			String description) throws NoSuchUserException {

		if (name != null)
			if (!(u.getName().equals(name)))
				u.setName(name);
		if (surname != null)
			if (!(u.getSurname().equals(surname)))
				u.setSurname(surname);
		if (website != null)
			if (!(website.equals(u.getWebsite())))
				u.setWebsite(website);
		if (location != null)
			if (!(location.equals(u.getLocation())))
				u.setLocation(location);
		if (description != null)
			if (!(description.equals(u.getDescription())))
					
				u.setDescription(description);
		if (minibio != null)
			if (!(minibio.equals(u.getMinibio())))
				u.setMinibio(minibio);
		// TODO gestire la dataif(!(u.getBirthdate().equals(birthdate))&&
		// !(birthdate.isEmpty()) && !(birthdate.equals(null)))
		// u.setBirthdate(Integer.parseInt(birthdate));

		manager.merge(u);
		return u;

	}

}
