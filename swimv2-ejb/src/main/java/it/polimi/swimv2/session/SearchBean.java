package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.Ability;
import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.remote.FriendShipBeanRemote;
import it.polimi.swimv2.session.remote.SearchBeanRemote;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class SearchBean implements SearchBeanRemote {

	@PersistenceContext(unitName="swimv2")
	private EntityManager manager;
	
	@EJB private FriendShipBeanRemote friendshipBean;
	
	@Override @SuppressWarnings("unchecked")
	public List<User> searchForHelp(List<String> abilities, int page, int pageSize) {
		StringBuilder builder = new StringBuilder("SELECT u FROM User u where 1=1");
		for(int i = 1; i <= abilities.size(); i++) {
			builder.append(" AND ?");
			builder.append(i);
			builder.append(" MEMBER OF u.abilities");
		}
		Query q = manager.createQuery(builder.toString());
		for(int i = 1; i <= abilities.size(); i++) {
			q.setParameter(i, abilities.get(i-1));
		}
		q.setFirstResult((page - 1) * pageSize);
		q.setMaxResults(pageSize);
		return q.getResultList();
	}
	
	@Override
	public long countSearchForHelp(List<String> abilities) {
		String initial = "SELECT COUNT(u) FROM User u where 1=1";
		Query q = buildSearchForHelpQuery(initial, abilities);
		return (Long) q.getSingleResult();
	}
	
	private Query buildSearchForHelpQuery(String initial, List<String> abilities) {
		StringBuilder builder = new StringBuilder(initial);
		for(int i = 1; i <= abilities.size(); i++) {
			builder.append(" AND ?");
			builder.append(i);
			builder.append(" MEMBER OF u.abilities");
		}
		Query q = manager.createQuery(builder.toString());
		for(int i = 1; i <= abilities.size(); i++) {
			q.setParameter(i, abilities.get(i-1));
		}
		return q;
	}
	
	@Override
	public List<User> searchForHelpAmongFriends(User u, List<String> abilities, int page, int pageSize) {
		ArrayList<User> list = new ArrayList<User>();
		List<User> res = searchForHelpAmongFriends(u, abilities);
		for(int i = (page - 1) * pageSize; i < page * pageSize && i < res.size(); i++) {
			list.add(res.get(i));
		}
		return list;
	}
	
	private List<User> searchForHelpAmongFriends(User u, List<String> abilities) {
		List<User> unfiltered = friendshipBean.getFriends(u);
		List<User> filtered = new ArrayList<User>();
		for(User friend : unfiltered) {
			if(isSubsetOf(abilities, friend.getAbilities())) {
				filtered.add(friend);
			}
		}
		return filtered;
	}
	

	private boolean isSubsetOf(List<String> abilities, Collection<Ability> userAbilities) {
		for(String ability : abilities) {
			if(!userAbilities.contains(new Ability(ability))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public long countSearchForHelpAmongFriends(User u, List<String> abilities) {
		return searchForHelpAmongFriends(u, abilities).size();
	}
	
}
