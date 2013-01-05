package it.polimi.swimv2.entity;

import java.io.Serializable;

import javax.persistence.Column;

public class FriendshipId implements Serializable {
	
	private static final long serialVersionUID = 6684163541032025055L;

	/* fields referencing User primary keys */
	@Column(name="user1")
	private int user1Id;
	@Column(name="user2")
	private int user2Id;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + user1Id;
		result = prime * result + user2Id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof FriendshipId)) {
			return false;
		}
		FriendshipId other = (FriendshipId) obj;
		if (user1Id != other.user1Id) {
			return false;
		}
		if (user2Id != other.user2Id) {
			return false;
		}
		return true;
	}
	
}
