package it.polimi.swimv2.session;

import java.util.List;

import javax.ejb.Remote;

import it.polimi.swimv2.entity.AbilityRequest;
import it.polimi.swimv2.entity.User;

@Remote
public interface AbilityBeanRemote {
	
	// non mi ricordo il senso di questo metodo...
	// List<Ability> searchForAbility(Ability[] ability);
	
	void requestAbility(String name, User user);
	
	List<AbilityRequest> retrievePendingRequests();	
	
	// TODO maybe it's better by name?
	
	void approveAbility(AbilityRequest request);
	
	void rejectAbility(AbilityRequest request);
	
}
