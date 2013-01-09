package it.polimi.swimv2.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

@NamedQueries({
	@NamedQuery(name="Message.findConversation", query="" +
			"SELECT m FROM Message m WHERE (m.sender = :user1 AND m.receiver = :user2) OR " +
			"(m.sender = :user2 AND m.receiver = :user1) ORDER BY m.timestamp DESC"),
	// TODO just hints on what needs to be done... 
	/* given a user, returns the set of all users having conversations with unread msgs (for the homepage???) */
	@NamedQuery(name="Message.findUnreadConversations", query="" + 
			"SELECT DISTINCT u FROM Message m JOIN m.sender u WHERE m.receiver = :user AND m.msgRead = false " + 
			"ORDER BY m.timestamp DESC"),
	@NamedQuery(name="Message.findUsersWithConversations", query="" + 
			"SELECT DISTINCT u FROM Message m JOIN m.sender u WHERE " +
			"u <> :user AND (m.receiver = :user OR m.sender = :user) " +
			"ORDER BY m.timestamp DESC")
})
@Entity
public class Message implements Serializable {

	private static final long serialVersionUID = 4032235999683631118L;

	@Id
	@GeneratedValue
	private int id;
	private Timestamp timestamp;
	private String text;
	@Column(columnDefinition = "bit default false")
	private boolean msgRead;

	@ManyToOne
	private User sender;

	@ManyToOne
	private User receiver;

	public Message() {
		super();
	}
	
	public Message(User from, User to, String text) {
		this.sender = from;
		this.receiver = to;
		this.text = text;
		this.timestamp = new Timestamp(System.currentTimeMillis());
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
	
	public void setMsgRead(boolean read) {
		this.msgRead = true;
	}
	
	public boolean isMsgRead() {
		return this.msgRead;
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
		if (!(obj instanceof Message)) {
			return false;
		}
		Message other = (Message) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", timestamp=" + timestamp + ", text="
				+ text + "]";
	}

}
