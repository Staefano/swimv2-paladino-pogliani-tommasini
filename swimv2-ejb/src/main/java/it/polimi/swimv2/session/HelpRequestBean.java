package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.Ability;
import it.polimi.swimv2.entity.Comment;
import it.polimi.swimv2.entity.Feedback;
import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.exceptions.ClosedHelpRequestException;
import it.polimi.swimv2.session.exceptions.NoSouchHRException;
import it.polimi.swimv2.session.remote.HelpRequestRemote;
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
		manager.persist(hr);
		return hr;
	}

	@Override
	public void addComment(HelpRequest hr, String comment, User sender)
			throws ClosedHelpRequestException {
		// for simplicity at the web tier level, if comment == null, does nothing
		Comment c = new Comment();
		if(!hr.isOpened()) {
			throw new ClosedHelpRequestException();
		}
		if(comment != null && !comment.trim().isEmpty()) {
			c.setSender(sender);
			c.setHelprequest(hr);
			c.setText(comment);
			manager.persist(c);
		}
	}

	@Override @SuppressWarnings("unchecked")
	public List<Comment> getComments(HelpRequest hr) {
		Query q = manager.createNamedQuery("Comment.getByHelpRequest");
		q.setParameter("hr", hr);
		return q.getResultList();
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

		} else {
			throw new ClosedHelpRequestException();
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HelpRequest> getOpenProvidedHR(User u) {
		
		Query q = manager.createNamedQuery("HelpRequest.findOpenedByHelper");
		q.setParameter("helper", u); 
		q.setParameter("accepted", RequestStatus.ACCEPTED);
		q.setParameter("waiting", RequestStatus.WAITING);
		q.setParameter("zombie", RequestStatus.ZOMBIE);

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
	public List<HelpRequest> getOpenRequestedHR(User u) {
		Query q = manager.createNamedQuery("HelpRequest.findOpenedByAsker");
		q.setParameter("asker", u); 
		q.setParameter("accepted", RequestStatus.ACCEPTED);
		q.setParameter("waiting", RequestStatus.WAITING);
		q.setParameter("zombie", RequestStatus.ZOMBIE);
		
		try{
			return (List<HelpRequest>) q.getResultList();
		}catch(NoResultException nre){
			return null;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HelpRequest> getClosedRequestedHR(User u) {
		Query q = manager.createNamedQuery("HelpRequest.findClosedByAsker");
		q.setParameter("asker", u); 
		q.setParameter("closed", RequestStatus.CLOSED);
		try{
			return (List<HelpRequest>) q.getResultList();
		} catch(NoResultException nre) {
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

	@Override
	public void refuseHR(HelpRequest hr) throws NoSouchHRException {
		Query commentQuery = manager.createNamedQuery("Comment.getByHelpRequest");
		commentQuery.setParameter("hr", hr);

		Query hrQuery = manager.createNamedQuery("HelpRequest.findHRByID");
		hrQuery.setParameter("id", hr.getId());
		try {
			manager.remove(hrQuery.getSingleResult());
			@SuppressWarnings("unchecked")
			List<Comment> commentList = (List<Comment>) commentQuery.getResultList();
			for (Comment comment : commentList) {
				manager.remove(comment);
			}
		} catch (NoResultException nre) {
			// Notification Bean: The HelpRequest doesn't not exit, deleting has failed
		}
	}

	@Override
	public void acceptHR(HelpRequest hr) throws NoSouchHRException {
		hr.setStatus(RequestStatus.ACCEPTED);
		manager.merge(hr);
	}

	@Override
	public void addFeedback(HelpRequest hr, int evaluation, String comment, Role role)
			throws ClosedHelpRequestException {
		
		if(hr.getStatus() != RequestStatus.CLOSED) {
			Feedback f = new Feedback();
			f.setEvaluation(evaluation);
			f.setString(comment);
			f.setRole(role);
			
			if(role.equals(Role.ASKER)){
				
				hr.setAskerFeedback(f);
				hr.setStatus(RequestStatus.ZOMBIE);
	
			}else if(role.equals(Role.HELPER)){
				hr.setReceiverFeedback(f);
				hr.setStatus(RequestStatus.CLOSED);
	
			}
			
			 manager.persist(f);
			 manager.merge(hr);
		}
		else {
			throw new ClosedHelpRequestException();
		}
		
	}

}
