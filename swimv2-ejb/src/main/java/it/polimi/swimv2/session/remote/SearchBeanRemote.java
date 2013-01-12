package it.polimi.swimv2.session.remote;

import javax.ejb.Remote;

import java.util.List;

import it.polimi.swimv2.entity.User;

@Remote
public interface SearchBeanRemote {

	/**
	 * Search users with the specified abilities
	 * 
	 * @param abilities
	 *            a list of strings representing abilities. The search performs
	 *            exact matching.
	 * @return a list of User object representing all the users who declared at
	 *         least one of the specified abilities
	 */
	List<User> searchForHelp(List<String> abilities);

}
