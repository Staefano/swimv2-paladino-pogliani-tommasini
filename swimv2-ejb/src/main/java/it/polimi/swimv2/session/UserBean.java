package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.Feedback;
import it.polimi.swimv2.entity.HelpRequest;
import it.polimi.swimv2.entity.Notification;
import it.polimi.swimv2.entity.User;
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
	public void editProfile(User newUser) throws NoSuchUserException {

		manager.merge(newUser);
		
	}

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

}
