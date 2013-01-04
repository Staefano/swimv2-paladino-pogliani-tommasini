package it.polimi.swimv2.entity;

import it.polimi.swimv2.enums.Role;
import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

@Entity
public class Feedback implements Serializable {

	@Id
	@GeneratedValue
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

	@Override
	public String toString() {
		return "Feedback [id=" + id + ", evaluation=" + evaluation
				+ ", String=" + String + ", role=" + role + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Feedback))
			return false;
		Feedback other = (Feedback) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
