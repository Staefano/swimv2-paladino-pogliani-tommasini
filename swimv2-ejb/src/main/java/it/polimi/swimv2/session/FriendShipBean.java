package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.Friendship;
import it.polimi.swimv2.entity.User;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class FriendShipBean implements FriendShipBeanRemote {

	
	@PersistenceContext(unitName = "swimv2")
	private EntityManager manager;

	// TODO marcello: ho modificato il metodo in questo modo, cambiandone la signature.
	// Per le amicizie indirette vedremo, 
	// eviterei di esporre al web tier l'oggetto friendship, e' troppo di basso livello.
	// io comunque metterei altri metodi per gestire la cosa, visto che e' rilevante
	// soltanto in pochi e particolari casi.
	@Override
	public List<User> getFriends(User u) {
		Query q = manager.createNamedQuery("Friendship.getByUser");
		q.setParameter("user",  u);
		@SuppressWarnings("unchecked")
		List<Friendship> friends = q.getResultList();
		List<User> users = new ArrayList<User>(friends.size());
		for(Friendship f : friends) {
			if(f.getUser1().equals(u)) {
				users.add(f.getUser2());
			} else {
				users.add(f.getUser1());
			}
		}
		return users;
	}

	@Override
	public void createFriendship(User asker, User receiver) {

		Friendship f = new Friendship();
		
		if(!(isFriend(asker, receiver))){
			f.setUser1(asker);
			f.setUser2(receiver);
			manager.persist(f);
		}
		
		
	}
	
	@Override
	public boolean isFriend(User asker, User receiver) {

		Query q = manager.createNamedQuery("Friendship.isFriend");
		q.setParameter("user1", asker);
		q.setParameter("user2", receiver);

		try{
			return (q.getSingleResult()!=null);
			
		}catch(NoResultException nre){
			
			return false;
			
		}
		
		
	}


}
