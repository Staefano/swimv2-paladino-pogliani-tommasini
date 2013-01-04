package it.polimi.swimv2.entity;

import java.io.Serializable;

import javax.persistence.Column;

public class FriendshipId implements Serializable {
	
	private static final long serialVersionUID = 6684163541032025055L;

	/* fields referencing User primary keys */
	@Column(name="user1")
	private int user1_id;
	@Column(name="user2")
	private int user2_id;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + user1_id;
		result = prime * result + user2_id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof FriendshipId))
			return false;
		FriendshipId other = (FriendshipId) obj;
		if (user1_id != other.user1_id)
			return false;
		if (user2_id != other.user2_id)
			return false;
		return true;
	}
	
}
