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
	public List<User> searchForHelp(List<String> abilities) {
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
		return q.getResultList();
	}
	
	@Override
	public List<User> searchForHelpAmongFriends(User u, List<String> abilities) {
		// TODO questa roba fa abbastanza schifo ed e' abbastanza inefficiente...
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
		// TODO non mi piace proprio per niente creare una new Ability tutte le volte!
		for(String ability : abilities) {
			if(!userAbilities.contains(new Ability(ability))) {
				return false;
			}
		}
		return true;
	}
	
}
