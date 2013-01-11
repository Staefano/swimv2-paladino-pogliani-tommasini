package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.exceptions.NoResultFoundException;

public interface UserImageBeanRemote {

	User setImage(int userId, byte[] img);

	int getImageIdByUserId(int userId) throws NoResultFoundException;

	byte[] getImage(long id) throws NoResultFoundException;

}
