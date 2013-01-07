package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.User;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class SearchBean implements SearchBeanRemote {

	@PersistenceContext(unitName="swimv2")
	EntityManager manager;
	
	@Override @SuppressWarnings("unchecked")
	public List<User> searchForHelp(List<String> abilities) {
		String qlString = "SELECT u FROM User u, IN (u.abilities) a WHERE a.name IN (:abilities)"; 
		Query q = manager.createQuery(qlString);
		q.setParameter("abilities", abilities);
		return q.getResultList();
	}
	
}
