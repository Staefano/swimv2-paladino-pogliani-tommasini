package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.entity.UserImage;
import it.polimi.swimv2.session.exceptions.NoResultFoundException;

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
		// remove the old image and substitute it with the new one
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
			throw new NoResultFoundException();
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

}
