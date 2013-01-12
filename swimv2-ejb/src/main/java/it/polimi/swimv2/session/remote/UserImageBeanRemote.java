package it.polimi.swimv2.session.remote;

import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.exceptions.NoResultFoundException;

public interface UserImageBeanRemote {

	/**
	 * Upload a new image and sets it as the profile picture of the specified
	 * user, removing the former profile picture (if any)
	 * 
	 * @param userId
	 *            the id of the user to be updated
	 * @param img
	 *            the byte stream of the image, in png format, to be uploaded
	 * @return the User object corresponding to the specified userId and updated
	 *         with the new image
	 */
	User setImage(int userId, byte[] img);

	/**
	 * Find the profile picture of the specified user
	 * 
	 * @return the ID of the image set as profile picture by the specified user
	 * @throws NoResultFoundException
	 *             if the userId don't correspond to any user or the user has no
	 *             profile image
	 */
	int getImageIdByUserId(int userId) throws NoResultFoundException;

	/**
	 * Fetch the image by ID
	 * 
	 * @param id
	 *            the ID of the image to be fetched
	 * @return the byte stream containing the image compressed in PNG format
	 * @throws NoResultFoundException
	 *             if the ID has no correspondence
	 */
	byte[] getImage(long id) throws NoResultFoundException;

	/**
	 * Remove the profile picture
	 * 
	 * @param updated
	 *            the User object corresponding to the specified user and
	 *            updated by unsetting the image
	 * @return the updated User object
	 */
	User unsetImage(User updated);

}
