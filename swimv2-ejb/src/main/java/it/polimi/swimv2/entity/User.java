package it.polimi.swimv2.entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

@Entity
@Table(name="users")
public class User implements Serializable {

	
	@Id @GeneratedValue private int id;
	private String name;
	private String surname;
	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}   
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
   
}
