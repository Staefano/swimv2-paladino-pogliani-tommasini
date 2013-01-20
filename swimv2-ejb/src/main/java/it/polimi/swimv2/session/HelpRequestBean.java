package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.Ability;
import it.polimi.swimv2.entity.Comment;
import it.polimi.swimv2.entity.Feedback;
import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.exceptions.ClosedHelpRequestException;
import it.polimi.swimv2.session.exceptions.NoSouchHRException;
import it.polimi.swimv2.session.remote.HelpRequestRemote;
import it.polimi.swimv2.entity.HelpRequest;
import it.polimi.swimv2.enums.FeedbackValue;
import it.polimi.swimv2.enums.RequestStatus;
import it.polimi.swimv2.enums.Role;

import java.util.HashSet;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class HelpRequestBean implements HelpRequestRemote {

	@PersistenceContext(unitName = "swimv2")
	private EntityManager manager;

	@Override
	public HelpRequest askForHelp(User sender, User receiver, String subject,
			List<Ability> abilties) {

		HelpRequest hr = new HelpRequest();
		HashSet<Ability> setAbility = new HashSet<Ability>();
		hr.setReceiver(receiver);
		hr.setSender(sender);
		hr.setSubject(subject);
		hr.setStatus(RequestStatus.WAITING);
		for (Ability a : abilties) {
			setAbility.add(a);
		}
		hr.setAbilities(setAbility);
		manager.persist(hr);
		return hr;
	}

	@Override
	public void addComment(HelpRequest hr, String comment, User sender)
			throws ClosedHelpRequestException {
		// for simplicity at the web tier level, if comment == null, does
		// nothing
		Comment c = new Comment();
		if (!hr.isOpened()) {
			throw new ClosedHelpRequestException();
		}
		if (comment != null && !comment.trim().isEmpty()) {
			c.setSender(sender);
			c.setHelprequest(hr);
			c.setText(comment);
			manager.persist(c);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Comment> getComments(HelpRequest hr) {
		Query q = manager.createNamedQuery("Comment.getByHelpRequest");
		q.setParameter("hr", hr);
		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HelpRequest> getOpenProvidedHR(User u) {

		Query q = manager.createNamedQuery("HelpRequest.findOpenedByHelper");
		q.setParameter("helper", u);
		q.setParameter("accepted", RequestStatus.ACCEPTED);
		q.setParameter("waiting", RequestStatus.WAITING);
		q.setParameter("zombie", RequestStatus.ZOMBIE);

		try {
			return (List<HelpRequest>) q.getResultList();
		} catch (NoResultException nre) {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HelpRequest> getClosedProvidedHR(User u) {

		Query q = manager.createNamedQuery("HelpRequest.findClosedByHelper");
		q.setParameter("helper", u);
		q.setParameter("closed", RequestStatus.CLOSED);

		try {
			return (List<HelpRequest>) q.getResultList();
		} catch (NoResultException nre) {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HelpRequest> getOpenRequestedHR(User u) {
		Query q = manager.createNamedQuery("HelpRequest.findOpenedByAsker");
		q.setParameter("asker", u);
		q.setParameter("accepted", RequestStatus.ACCEPTED);
		q.setParameter("waiting", RequestStatus.WAITING);
		q.setParameter("zombie", RequestStatus.ZOMBIE);

		try {
			return (List<HelpRequest>) q.getResultList();
		} catch (NoResultException nre) {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HelpRequest> getClosedRequestedHR(User u) {
		Query q = manager.createNamedQuery("HelpRequest.findClosedByAsker");
		q.setParameter("asker", u);
		q.setParameter("closed", RequestStatus.CLOSED);
		try {
			return (List<HelpRequest>) q.getResultList();
		} catch (NoResultException nre) {
			return null;
		}

	}

	@Override
	public HelpRequest findByID(int id) throws NoSouchHRException {
		Query q = manager.createNamedQuery("HelpRequest.findHRByID");
		q.setParameter("id", id);

		try {
			return (HelpRequest) q.getSingleResult();

		} catch (NoResultException e) {
			throw new NoSouchHRException(e);
		}
	}

	@Override
	public void refuseHR(HelpRequest hr) throws NoSouchHRException {
		Query commentQuery = manager
				.createNamedQuery("Comment.getByHelpRequest");
		commentQuery.setParameter("hr", hr);

		Query hrQuery = manager.createNamedQuery("HelpRequest.findHRByID");
		hrQuery.setParameter("id", hr.getId());
		try {
			manager.remove(hrQuery.getSingleResult());
			@SuppressWarnings("unchecked")
			List<Comment> commentList = (List<Comment>) commentQuery
					.getResultList();
			for (Comment comment : commentList) {
				manager.remove(comment);
			}
		} catch (NoResultException nre) {
			// Notification Bean: The HelpRequest doesn't not exit, deleting has
			// failed
		}
	}

	@Override
	public void acceptHR(HelpRequest hr) throws NoSouchHRException {
		hr.setStatus(RequestStatus.ACCEPTED);
		manager.merge(hr);
	}
	
	@Override
	public void addFeedback(HelpRequest request, FeedbackValue evaluation,
			String comment, User user) throws ClosedHelpRequestException {
		// NB user is the user who IS CURRENTLY LOGGED IN (i.e., who is giving the feedback!)
		if(!request.canPlaceFeedback(user)) {
			throw new ClosedHelpRequestException();
		}

		Feedback feedback = new Feedback();
		feedback.setEvaluation(evaluation);
		feedback.setComment(comment);
		Role role;
		User other;

		if(request.getSender().equals(user)) {
			role = Role.HELPER;
			request.setReceiverFeedback(feedback);
			request.setStatus(RequestStatus.ZOMBIE);
			other = request.getReceiver();
		} else {
			role = Role.ASKER;
			request.setAskerFeedback(feedback);
			request.setStatus(RequestStatus.CLOSED);
			other = request.getSender();
		}
		feedback.setRole(role);
		// add the feedback to the user to pre-compute aggregate values
		
		other.addFeedback(evaluation, role);
		// save it all
		manager.persist(feedback);
		manager.merge(request);
		manager.merge(other);
	}

}
