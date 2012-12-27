package it.polimi.swimv2.entity;

import java.io.Serializable;
import java.lang.String;
import java.sql.Timestamp;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: AbilityRequest
 *
 */
@Entity

public class AbilityRequest implements Serializable {

	   
	@Id
	private int id;
	private String ability;
	private Timestamp timestamp;
	private String comment;
	private static final long serialVersionUID = 1L;

	public AbilityRequest() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getAbility() {
		return this.ability;
	}

	public void setAbility(String ability) {
		this.ability = ability;
	}   
	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}   
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
   
}
