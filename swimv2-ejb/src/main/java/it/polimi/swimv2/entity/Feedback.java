package it.polimi.swimv2.entity;

import it.polimi.swimv2.enums.Role;
import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;


/**
 * Entity implementation class for Entity: Feedback
 *
 */
@Entity

public class Feedback implements Serializable {

	   
	@Id
	private int id;
	private int evaluation;
	private String String;
	private Role role;
	private static final long serialVersionUID = 1L;

	public Feedback() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public int getEvaluation() {
		return this.evaluation;
	}

	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}   
	public String getString() {
		return this.String;
	}

	public void setString(String String) {
		this.String = String;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
   
}
