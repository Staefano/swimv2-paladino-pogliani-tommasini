package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.Ability;
import it.polimi.swimv2.entity.AbilityRequest;
import it.polimi.swimv2.entity.User;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class AbilityBean implements AbilityBeanRemote {

	@PersistenceContext(unitName = "swimv2")
	EntityManager manager;

	@EJB
	NotificationBeanRemote notificationBean;

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
			if (choice.equals("approve")) {
				notificationBean.notifyAbilityAccepted(r);
			} else {
				// TODO notificationBean.notifyAbilityRejected(request);
			}
			manager.remove(r);
		}

	}

}
