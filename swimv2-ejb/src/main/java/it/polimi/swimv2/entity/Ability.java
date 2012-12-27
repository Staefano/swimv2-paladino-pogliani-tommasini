package it.polimi.swimv2.entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;
import it.polimi.swimv2.entity.User;
/**
 * Entity implementation class for Entity: Ability
 *
 */
@Entity

public class Ability implements Serializable {

	   
	@Id
	private String name;
	private User user;
	private static final long serialVersionUID = 1L;

	public Ability() {
		super();
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
   
}
