package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.Ability;
import it.polimi.swimv2.entity.AbilityRequest;
import it.polimi.swimv2.entity.User;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

@Stateless
public class AbilityBean implements AbilityBeanRemote {

	@PersistenceUnit(name="swimv2")
	EntityManager manager;
	
	@Override
	public void requestAbility(String name, User user) {
		AbilityRequest request = new AbilityRequest();
		request.setAbility(name);
		request.setSender(user);
		request.setTimestamp(new Timestamp(System.currentTimeMillis()));
		manager.persist(request);
	}

	@Override
	public List<AbilityRequest> retrievePendingRequests() {
		Query q = manager.createQuery("SELECT a FROM AbiltyRequest a ORDER BY a.timestamp DESC");
		@SuppressWarnings("unchecked")
		List<AbilityRequest> l = q.getResultList();
		return l;
	}

	@Override
	public void approveAbility(AbilityRequest request) {
		List<AbilityRequest> reqs = getWithSameName(request);
		// TODO notification for all involved users!
		Ability newAbility = new Ability();
		newAbility.setName(request.getAbility());
		for(AbilityRequest r : reqs) {
			manager.remove(r);
		}
	}
	
	@Override
	public void rejectAbility(AbilityRequest request) {
		List<AbilityRequest> reqs = getWithSameName(request);
		// we delete outright the requests, without even informing people!
		for(AbilityRequest r : reqs) {
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

}
