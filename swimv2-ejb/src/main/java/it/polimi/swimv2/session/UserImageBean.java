package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.entity.UserImage;
import it.polimi.swimv2.session.exceptions.NoResultFoundException;
import it.polimi.swimv2.session.remote.UserImageBeanRemote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UserImageBean implements UserImageBeanRemote {

	@PersistenceContext(unitName = "swimv2")
	private EntityManager manager;

	@Override
	public User setImage(int userId, byte[] img) {
		UserImage image = new UserImage(img);
		User jpaUser = manager.find(User.class, userId);
		UserImage old = jpaUser.getImage();
		manager.persist(image);
		jpaUser.setImage(image);
		if (old != null) {
			manager.remove(old);
		}
		return manager.merge(jpaUser);
	}
	
	@Override
	public int getImageIdByUserId(int userId) throws NoResultFoundException {
		Query q = manager.createNamedQuery("UserImage.getIDByUserId");
		q.setParameter("userid",  userId);
		try {
			Long num = (Long) q.getSingleResult();
			if(num == null) {
				throw new NoResultFoundException();
			} else {
				return num.intValue();
			}
		} catch(NoResultException e) {
			throw new NoResultFoundException(e);
		}
	}
	
	@Override
	public byte[] getImage(long id) throws NoResultFoundException {
		UserImage img = manager.find(UserImage.class, id);
		if(img != null) {
			return img.getImage();
		} else {
			throw new NoResultFoundException();
		}
	}

	@Override
	public User unsetImage(User user) {
		User jpaUser = manager.find(User.class, user.getId());
		UserImage img = jpaUser.getImage();
		jpaUser.setImage(null);
		jpaUser = manager.merge(jpaUser);
		if(img != null) {
			manager.remove(img);
		}
		return jpaUser;
	}

}
