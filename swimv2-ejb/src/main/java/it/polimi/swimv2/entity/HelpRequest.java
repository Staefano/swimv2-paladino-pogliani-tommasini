package it.polimi.swimv2.entity;

import it.polimi.swimv2.enums.RequestStatus;
import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: HelpRequest
 *
 */
@Entity

public class HelpRequest implements Serializable {

	   
	@Id @GeneratedValue	
	private int id;

	private String subject;
	private RequestStatus status;
	private static final long serialVersionUID = 1L;
	
	@OneToOne(optional = true)
	Feedback askerFeedback;
	
	@OneToOne(optional = true)
	Feedback receiverFeedback;
	
	// ManyToMany abilities
	
	@ManyToOne
	private User sender;
	
	@ManyToOne
	private User receiver;

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
   
}
