package it.polimi.swimv2.entity;

import java.io.Serializable;
import java.lang.String;
import java.sql.Timestamp;
import javax.persistence.*;

@Entity

public class Message implements Serializable {
	
	@Id private int id;
	private Timestamp timestamp;
	private String text;
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private User sender;
	
	@ManyToOne
	private User receiver;

	public Message() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}   
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
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
   
}
