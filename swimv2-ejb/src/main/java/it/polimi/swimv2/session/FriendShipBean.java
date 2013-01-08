package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.Comment;
import it.polimi.swimv2.entity.Friendship;
import it.polimi.swimv2.entity.User;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class FriendShipBean
 */
@Stateless
public class FriendShipBean implements FriendShipBeanRemote {

	
	@PersistenceContext(unitName = "swimv2")
	private EntityManager manager;

	@Override
	public List<Friendship> getFriendship(User u) {
		
		Query q = manager.createNamedQuery("Friendship.getByUSer");
		q.setParameter("userId", u.getId());

		@SuppressWarnings("unchecked")
		List<Friendship> friends = q.getResultList();
		return friends;
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
		q.setParameter("user1Id", asker.getId());
		q.setParameter("user2Id", receiver.getId());

		try{
			return (q.getSingleResult()!=null);
			
		}catch(NoResultException nre){
			
			return false;
			
		}
		
		
	}


}
