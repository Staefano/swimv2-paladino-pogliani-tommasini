package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.Ability;
import it.polimi.swimv2.entity.Comment;
import it.polimi.swimv2.entity.Feedback;
import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.exceptions.ClosedHelpRequestException;
import it.polimi.swimv2.session.exceptions.NoSouchHRException;
import it.polimi.swimv2.entity.HelpRequest;
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

		Comment c = new Comment();

		c.setHelprequest(hr);
		c.setSender(sender);
		c.setText(subject);

		manager.persist(c);
		manager.persist(hr);

		return hr;

	}

	@Override
	public void addComment(HelpRequest hr, String comment, User sender)
			throws ClosedHelpRequestException {

		Comment c = new Comment();

		if (hr.isOpened()) {

			c.setSender(sender);
			c.setHelprequest(hr);
			c.setText(comment);

			manager.persist(c);

		} else
			throw new ClosedHelpRequestException();

	}

	@Override
	public List<Comment> getComments(HelpRequest hr) {

		Query q = manager.createNamedQuery("Comment.getByHelpRequest");
		q.setParameter("hr", hr);

		@SuppressWarnings("unchecked")
		List<Comment> comments = q.getResultList();
		return comments; // TODO bisogna catchare l'eccezione di no result?

	}

	@Override
	public void giveFeedback(int value, String comment, HelpRequest hr,
			User user) throws ClosedHelpRequestException {

		if (hr.isOpened()) {

			Feedback f = new Feedback();
			f.setEvaluation(value);
			f.setString(comment);
			if (user.equals(hr.getReceiver())) {
				f.setRole(Role.ASKER);
				hr.setAskerFeedback(f);
				// If the user who is giving the feedback is the receiver of the
				// hr
				// the feedbackrole should be "asker"
			} else if (user.equals(hr.getSender())) {
				f.setRole(Role.HELPER);
				hr.setReceiverFeedback(f);
				// If the user who is giving the feedback is the sender of the
				// hr
				// the feedbackrole should be "helper"
			}

			manager.persist(f);
			manager.merge(hr);

		} else
			throw new ClosedHelpRequestException();

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HelpRequest> getOpenProvidedHR(User u) {
		
		Query q = manager.createNamedQuery("HelpRequest.findOpenedByHelper");
		q.setParameter("helper", u); 
		q.setParameter("accepted", RequestStatus.ACCEPTED);
		q.setParameter("waiting", RequestStatus.WAITING);

		try{
			return (List<HelpRequest>) q.getResultList();
		}catch(NoResultException nre){
			return null;
		}
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HelpRequest> getClosedProvidedHR(User u) {

		Query q = manager.createNamedQuery("HelpRequest.findClosedByHelper");
		q.setParameter("helper", u); 
		q.setParameter("closed", RequestStatus.CLOSED);

		try{
			return (List<HelpRequest>) q.getResultList();
		}catch(NoResultException nre){
			return null;
		}
	
	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HelpRequest> getOpenReceivedHR(User u) {

		Query q = manager.createNamedQuery("HelpRequest.findOpenedByAsker");
		q.setParameter("asker", u); 
		q.setParameter("accepted", RequestStatus.ACCEPTED);
		q.setParameter("waiting", RequestStatus.WAITING);

		try{
			return (List<HelpRequest>) q.getResultList();
		}catch(NoResultException nre){
			return null;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HelpRequest> getClosedReceiveddHR(User u) {

		Query q = manager.createNamedQuery("HelpRequest.findClosedByAsker");
		q.setParameter("asker", u); 
		q.setParameter("closed", RequestStatus.CLOSED);

		try{
			return (List<HelpRequest>) q.getResultList();
		}catch(NoResultException nre){
			return null;
		}
	
	}

	@Override
	public HelpRequest findByID(int id) throws NoSouchHRException {

		Query q = manager.createNamedQuery("HelpRequest.findHRByID");
		q.setParameter("id", id); 
		
		try{
			return (HelpRequest) q.getSingleResult();
			
		}catch(NoResultException nre){
			throw new NoSouchHRException();
		}
	}
}
