package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.Ability;
import it.polimi.swimv2.entity.AbilityRequest;
import it.polimi.swimv2.entity.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class AbilityBean implements AbilityBeanRemote {

	@PersistenceContext(unitName = "swimv2")
	EntityManager manager;

	@Override
	public void requestAbility(String name, String comment, User user) {
		AbilityRequest request = new AbilityRequest();
		request.setAbility(name);
		request.setComment(comment);
		request.setSender(user);
		request.setTimestamp(new Timestamp(System.currentTimeMillis()));
		manager.persist(request);
	}

	@Override
	public List<AbilityRequest> retrievePendingRequests() {
		Query q = manager
				.createQuery("SELECT a FROM AbilityRequest a ORDER BY a.timestamp DESC");
		@SuppressWarnings("unchecked")
		List<AbilityRequest> list = q.getResultList();
		return list;
	}

	@Override
	public void addNewAbility(String name) {
		Ability newAbility = new Ability();
		newAbility.setName(name);
		manager.persist(newAbility);
	}

	@Override
	public void rejectAbility(AbilityRequest request) {
		List<AbilityRequest> reqs = getWithSameName(request);
		for (AbilityRequest r : reqs) {
			manager.remove(r);
		}
	}

	private List<AbilityRequest> getWithSameName(AbilityRequest request) {
		Query q = manager.createNamedQuery("AbilityRequest.findByName");
		q.setParameter("name", request.getAbility());
		@SuppressWarnings("unchecked")
		List<AbilityRequest> reqs = q.getResultList();
		return reqs;
	}

	@Override
	public AbilityRequest getRequest(int abId) {
		Query q = manager
				.createQuery("SELECT a FROM AbilityRequest a WHERE a.id = :abId");
		q.setParameter("abId", abId);
		return (AbilityRequest) q.getSingleResult();
	}

	@Override
	public void removeAbilityRequest(AbilityRequest request, String choice) {
		List<AbilityRequest> reqs = getWithSameName(request);
		for (AbilityRequest r : reqs) {
			manager.remove(r);
		}

	}

	@Override
	public boolean alreadyExist(String ability) {
		Query q = manager
				.createQuery("SELECT a FROM Ability a WHERE a.name = :name");
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
				.createQuery("SELECT a FROM Ability a WHERE a.name=:name");
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
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Ability> searchAbility(String queryString) {
		Query q = manager.createNamedQuery("Ability.searchAbility");
		q.setParameter("name", '%' + queryString.toLowerCase().trim() + '%');
		return q.getResultList();
	}
	
}
