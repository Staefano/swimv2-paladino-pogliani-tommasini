package it.polimi.swimv2.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name="AbilityRequest.findByName",
			query="SELECT a FROM AbilityRequest a WHERE a.ability = :name"),
	@NamedQuery(name="AbilityRequest.retrieveAll",
			query="SELECT a FROM AbilityRequest a ORDER BY a.timestamp DESC")
})

public class AbilityRequest implements Serializable {

	@Id
	@GeneratedValue
	private int id;
	private String ability;
	private Timestamp timestamp;
	private String comment;
	private static final long serialVersionUID = 1L;

	@ManyToOne
	private User sender;

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

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	@Override
	public String toString() {
		return "AbilityRequest [id=" + id + ", ability=" + ability
				+ ", timestamp=" + timestamp + ", comment=" + comment
				+ ", sender=" + sender + "]";
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
		if (!(obj instanceof AbilityRequest)) {
			return false;
		}
		AbilityRequest other = (AbilityRequest) obj;
		return id != other.id;
	}

}
