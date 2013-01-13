package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.remote.SearchBeanRemote;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class SearchBean implements SearchBeanRemote {

	@PersistenceContext(unitName="swimv2")
	private EntityManager manager;
	
	@Override @SuppressWarnings("unchecked")
	public List<User> searchForHelp(List<String> abilities) {
		String query = "SELECT u FROM User u where 1=1";
		for(int i = 1; i <= abilities.size(); i++) {
			query += " AND ?" + i + " MEMBER OF u.abilities";
		}
		Query q = manager.createQuery(query);
		for(int i = 1; i <= abilities.size(); i++) {
			q.setParameter(i, abilities.get(i-1));
		}
		return q.getResultList();
	}
	
}
