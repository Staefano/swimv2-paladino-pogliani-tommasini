package it.polimi.swimv2.entity;

import java.io.Serializable;
import java.lang.String;
import java.sql.Timestamp;
import javax.persistence.*;

@Entity
public class Comment implements Serializable {

	@Id
	@GeneratedValue
	private int id;
	private Timestamp timestamp;
	private String text;
	private static final long serialVersionUID = 1L;

	@OneToOne
	private User sender;

	@OneToOne
	private HelpRequest helprequest;

	public Comment() {
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
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public HelpRequest getHelprequest() {
		return helprequest;
	}

	public void setHelprequest(HelpRequest helprequest) {
		this.helprequest = helprequest;
	}

}
