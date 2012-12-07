package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.User;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface ProvaBeanRemote {

	String getHello();

	List<User> getAllUsers();

	void addUser(String name, String surname);

}
