package it.polimi.swimv2.entity;

import it.polimi.swimv2.enums.RequestStatus;
import java.io.Serializable;
import java.lang.String;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name="HelpRequest.findByHelper",
			query="SELECT x FROM HelpRequest x WHERE x.receiver = :helper"),
	
	@NamedQuery(name="HelpRequest.findByAsker",
			query="SELECT x FROM HelpRequest x WHERE x.sender = :asker"),
	
	@NamedQuery(name="HelpRequest.findByStatus",
	query="SELECT x FROM HelpRequest x WHERE x.status = :status")

})
public class HelpRequest implements Serializable {

	@Id
	@GeneratedValue
	private int id;
	private String subject;
	private RequestStatus status;
	private Timestamp timestamp;
	private static final long serialVersionUID = 1L;

	@ManyToOne
	private User sender;

	@ManyToOne
	private User receiver;

	@OneToOne(optional = true)
	private Feedback askerFeedback;

	@OneToOne(optional = true)
	private Feedback receiverFeedback;

	// manytomany unidirezionale dato che ability non tiene traccia della helprequest
	@ManyToMany
	@JoinTable(name = "HelpRequestAbility", 
				joinColumns = { @JoinColumn(name = "request") }, 
				inverseJoinColumns = { @JoinColumn(name = "ability") })
	private Set<Ability> abilities;

	public HelpRequest() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public RequestStatus getStatus() {
		return this.status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	public User getSender() {
		return this.sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return this.receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public Feedback getAskerFeedback() {
		return this.askerFeedback;
	}

	public void setAskerFeedback(Feedback feedback) {
		this.askerFeedback = feedback;
	}

	public Feedback getReceiverFeedback() {
		return this.receiverFeedback;
	}

	public void setReceiverFeedback(Feedback feedback) {
		this.receiverFeedback = feedback;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
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
		if (!(obj instanceof HelpRequest))
			return false;
		HelpRequest other = (HelpRequest) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HelpRequest [id=" + id + ", subject=" + subject + ", status="
				+ status + ", timestamp=" + timestamp + " ...]";
	}
	
}
