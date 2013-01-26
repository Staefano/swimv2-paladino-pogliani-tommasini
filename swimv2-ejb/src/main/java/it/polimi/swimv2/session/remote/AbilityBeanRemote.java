package it.polimi.swimv2.session.remote;

import java.util.List;

import javax.ejb.Remote;

import it.polimi.swimv2.entity.Ability;
import it.polimi.swimv2.entity.AbilityRequest;
import it.polimi.swimv2.entity.User;

@Remote
public interface AbilityBeanRemote {
		
	/**
	 * return an ability request by id
	 */
	AbilityRequest getRequest(int abId);
	
	/**
	 * add a new abilityrequest with user as asker
	 */
	void requestAbility(String name, String comment, User user);
		
	/**
	 * add a new ability in the system
	 */
	void addNewAbility(String name);
	
	/**
	 * refuse the request AbilityRequest
	 */
	void rejectAbility(AbilityRequest request);

	/**
	 * remove the abilityrequest from the system
	 */
	void removeAbilityRequest(AbilityRequest request);
	
	/**
	 * is true if and Ability a whose name is 
	 * @param ability
	 */
	boolean alreadyExist(String ability);

	/**
	 * @return the list of the pending abilityrequest
	 */
	List<AbilityRequest> retrievePendingRequests();	
	
	/**
	 * @return the list of all abilities
	 */
	List<Ability> getAbilities(String[] abilityNames);

	/**
	 * @return the list of ability s
	 */
	List<Ability> searchAbility(String queryString, User user);
	
}
