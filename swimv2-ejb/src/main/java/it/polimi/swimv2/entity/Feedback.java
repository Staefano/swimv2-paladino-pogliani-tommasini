package it.polimi.swimv2.entity;

import it.polimi.swimv2.enums.FeedbackValue;
import it.polimi.swimv2.enums.Role;
import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Feedback implements Serializable {

	@Id
	@GeneratedValue
	private int id;
	
	private FeedbackValue evaluation;
	
	private String comment;
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

	public FeedbackValue getEvaluation() {
		return this.evaluation;
	}

	public void setEvaluation(FeedbackValue evaluation2) {
		this.evaluation = evaluation2;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String string) {
		this.comment = string;
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
				+ ", String=" + comment + ", role=" + role + "]";
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
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Feedback)) {
			return false;
		}
		Feedback other = (Feedback) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

}
