package it.polimi.swimv2.test;

import javax.ejb.Remote;

@Remote
public interface InitializerTestBeanRemote {
	
	void createPredefinedUsers();
	void deletePredefinedUsers();
	
}
