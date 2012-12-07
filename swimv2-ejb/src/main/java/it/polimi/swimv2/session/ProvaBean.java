package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class ProvaBean implements ProvaBeanRemote {

	@PersistenceContext(unitName="swimv2")
	EntityManager manager;
	
	@Override
	public String getHello() {
		return "Hello, World... from a stateless session bean";
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		return (List<User>) manager.createQuery("SELECT x FROM User x").getResultList();
	}
	
	@Override
	public void addUser(String name, String surname) {
		User u = new User();
		u.setName(name);
		u.setSurname(surname);
		manager.persist(u);
	}

}
