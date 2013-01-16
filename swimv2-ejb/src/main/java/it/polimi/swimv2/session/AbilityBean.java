package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.Ability;
import it.polimi.swimv2.entity.AbilityRequest;
import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.remote.AbilityBeanRemote;
import it.polimi.swimv2.session.remote.NotificationBeanRemote;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class AbilityBean implements AbilityBeanRemote {

	@PersistenceContext(unitName = "swimv2")
	private EntityManager manager;
	
	@EJB
	private NotificationBeanRemote notificationBean;
	
	@Override
	public void requestAbility(String name, String comment, User user) {
		AbilityRequest request = new AbilityRequest();
		request.setAbility(name);
		request.setComment(comment);
		request.setSender(user);
		request.setTimestamp(new Timestamp(System.currentTimeMillis()));
		manager.persist(request);
	}

	@Override @SuppressWarnings("unchecked")
	public List<AbilityRequest> retrievePendingRequests() {
		Query q = manager
				.createNamedQuery("AbilityRequest.retrieveAll");
		return q.getResultList();
	}

	@Override
	public void addNewAbility(String name) {
		// aggiungo la nuova ability
		Ability newAbility = new Ability();
		newAbility.setName(name);
		manager.persist(newAbility);
		
		
		// notifico chi aveva richiesto tale ability
		Query q = manager
				.createNamedQuery("AbilityRequest.retrieveAll");
		@SuppressWarnings("unchecked")
		List<AbilityRequest> reqs = q.getResultList();
		for (AbilityRequest r : reqs) {
			if( r.getAbility().equals(name) ) {
				notificationBean.notifyAbilityAccepted(r);
				removeAbilityRequest(r);
			}
		}
		
	}

	@Override
	public void rejectAbility(AbilityRequest request) {
		List<AbilityRequest> reqs = getWithSameName(request);
		for (AbilityRequest r : reqs) {
			manager.remove(r);
		}
	}

	@Override
	public AbilityRequest getRequest(int abId) {
		Query q = manager
				.createQuery("SELECT a FROM AbilityRequest a WHERE a.id = :abId");
		q.setParameter("abId", abId);
		return (AbilityRequest) q.getSingleResult();
	}

	@Override
	public void removeAbilityRequest(AbilityRequest request) {
		List<AbilityRequest> reqs = getWithSameName(request);
		for (AbilityRequest r : reqs) {
			manager.remove(r);
		}

	}

	@Override
	public boolean alreadyExist(String ability) {
		Query q = manager
				.createNamedQuery("Ability.searchByName");
		q.setParameter("name", ability);

		try {
			q.getSingleResult();
		} catch (NoResultException nre) {
			return false;
		}

		return true;
	}

	@Override
	public List<Ability> getAbilities(String[] abilityNames) {

		List<Ability> abilities = new ArrayList<Ability>();
		Query q = manager
				.createNamedQuery("Ability.searchByName");
		for (String aName : abilityNames) {
			
			q.setParameter("name", aName);
			
			try {
				abilities.add((Ability) q.getSingleResult());
			} catch (NoResultException nre) {
				// TODO gestire
			}

		}
		
		return abilities;

	}
	
	@Override @SuppressWarnings("unchecked")
	public List<Ability> searchAbility(String queryString, User user) {
		Query q = manager.createNamedQuery("Ability.searchAbility");
		q.setParameter("name", '%' + queryString.toLowerCase().trim() + '%');
		
		List<Ability> searchAbs = q.getResultList();
		Set<Ability> userAbs = user.getAbilities();
		List<Ability> abilities = new ArrayList<Ability>();
		
		for(Ability ab: searchAbs) {
			if(!(userAbs.contains(ab))) {
				abilities.add(ab);
			}
		}
		
		return abilities;
	}
	
	@SuppressWarnings("unchecked")
	private List<AbilityRequest> getWithSameName(AbilityRequest request) {
		Query q = manager.createNamedQuery("AbilityRequest.findByName");
		q.setParameter("name", request.getAbility());
		return q.getResultList();
	}
	
}
