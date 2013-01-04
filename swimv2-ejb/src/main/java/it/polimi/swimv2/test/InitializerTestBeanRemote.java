package it.polimi.swimv2.test;

import javax.ejb.Remote;

@Remote
public interface InitializerTestBeanRemote {
	
	public void createPredefinedUsers();
	public void deletePredefinedUsers();
	
}
