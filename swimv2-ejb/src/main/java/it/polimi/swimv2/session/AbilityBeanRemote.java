package it.polimi.swimv2.session;

import java.util.List;

import javax.ejb.Remote;

import it.polimi.swimv2.entity.Ability;
import it.polimi.swimv2.entity.AbilityRequest;
import it.polimi.swimv2.entity.User;

@Remote
public interface AbilityBeanRemote {
		
	void requestAbility(String name, String comment, User user);
	
	List<AbilityRequest> retrievePendingRequests();	
		
	void addNewAbility(String name);
	
	void rejectAbility(AbilityRequest request);

	AbilityRequest getRequest(int abId);
	
	void removeAbilityRequest(AbilityRequest request, String choice);

	boolean alreadyExist(String ability);
	
	List<Ability> getAbilities(String[] abilityNames);

	List<Ability> searchAbility(String queryString, User user);
	
}
