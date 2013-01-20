package it.polimi.swimv2.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;

@NamedQueries({
	/* NB - those queries are intended from user1 perspective (e.g. they are done by user1) */
	@NamedQuery(name="Message.findConversation", query="" +
			"SELECT m FROM Message m WHERE (m.sender = :user1 AND m.receiver = :user2 AND m.senderDeleted = false) OR " +
			"(m.sender = :user2 AND m.receiver = :user1 AND m.receiverDeleted = false) ORDER BY m.timestamp DESC"),
	@NamedQuery(name="Message.findUnreadConversations", query="" + 
			"SELECT DISTINCT u FROM Message m JOIN m.sender u WHERE m.receiver = :user AND m.msgRead = false AND m.receiverDeleted = false " + 
			"ORDER BY m.timestamp DESC"),
	@NamedQuery(name="Message.findUsersWithReceivedMessages", query="" + 
			"SELECT DISTINCT u FROM Message m JOIN m.sender u WHERE " +
			"u <> :user AND (m.receiver = :user AND m.receiverDeleted = false) " +
			"ORDER BY m.timestamp DESC"),
	@NamedQuery(name="Message.findUsersWithSentMessages", query="" + 
			"SELECT DISTINCT u FROM Message m JOIN m.receiver u WHERE " +
			"u <> :user AND (m.sender = :user AND m.senderDeleted = false) " +
			"ORDER BY m.timestamp DESC")
})
@Entity
public class Message implements Serializable {

	private static final long serialVersionUID = 4032235999683631118L;

	@Id
	@GeneratedValue
	private int id;
	
	/**
	 * Date and time of the message creation
	 */
	private Timestamp timestamp;
	
	/**
	 * Content of the message
	 */
	private String text;
	
	/**
	 * Set to true if the message was read (e.g. the conversation window was opened by the receiver at least once)
	 */
	@Column(columnDefinition = "bit default false")
	private boolean msgRead;
	
	@ManyToOne
	private User sender;

	@ManyToOne
	private User receiver;

	/**
	 * Marks the message as deleted by the user 'sender', and thus it's not displayed in
	 * any screen shown to the sender
	 */
	@Column(columnDefinition = "bit default false")
	private boolean senderDeleted;

	/**
	 * Marks the message as deleted by the user 'receiver', and thus it's not displayed
	 * in any scrren shown to the recipient
	 */
	@Column(columnDefinition = "bit default false")
	private boolean receiverDeleted;

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
		return (Timestamp) this.timestamp.clone();
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = (Timestamp) timestamp.clone();
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
	
	public boolean isSenderDeleted() {
		return this.senderDeleted;
	}
	
	public void setSenderDeleted(boolean senderDeleted) {
		this.senderDeleted = senderDeleted;
	}
	
	public boolean isReceiverDeleted() {
		return this.receiverDeleted;
	}
	
	public void setReceiverDeleted(boolean receiverDeleted) {
		this.receiverDeleted = receiverDeleted;
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
