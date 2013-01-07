package it.polimi.swimv2.session;

import javax.ejb.Remote;

import java.util.List;

import it.polimi.swimv2.entity.User;

@Remote
public interface SearchBeanRemote {
	
	List<User> searchForHelp(List<String> abilities);
	
}
