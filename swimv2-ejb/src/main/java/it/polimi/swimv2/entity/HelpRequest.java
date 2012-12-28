package it.polimi.swimv2.entity;

import it.polimi.swimv2.enums.RequestStatus;
import java.io.Serializable;
import java.lang.String;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.*;

@Entity
public class HelpRequest implements Serializable {

	@Id
	@GeneratedValue
	private int id;
	private String subject;
	private RequestStatus status;
	private Timestamp timestamp;
	private static final long serialVersionUID = 1L;

	@OneToOne
	private User sender;

	@OneToOne
	private User receiver;

	@OneToOne(optional = true)
	private Feedback askerFeedback;

	@OneToOne(optional = true)
	private Feedback receiverFeedback;

	// manytomany unidirezionale dato che ability non tiene traccia della
	// helprequest
	@ManyToMany
	@JoinTable(name = "HelpRequestAbility", joinColumns = { @JoinColumn(name = "request") }, inverseJoinColumns = { @JoinColumn(name = "ability") })
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

}
