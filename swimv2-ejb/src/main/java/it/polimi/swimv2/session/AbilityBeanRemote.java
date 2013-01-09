package it.polimi.swimv2.session;

import java.util.List;

import javax.ejb.Remote;

import it.polimi.swimv2.entity.AbilityRequest;
import it.polimi.swimv2.entity.User;

@Remote
public interface AbilityBeanRemote {
		
	void requestAbility(String name, User user);
	
	List<AbilityRequest> retrievePendingRequests();	
		
	void addNewAbility(String name);
	
	void rejectAbility(AbilityRequest request);

	AbilityRequest getRequest(int abId);
	
	void removeAbilityRequest(AbilityRequest request, String choice);
		
}
