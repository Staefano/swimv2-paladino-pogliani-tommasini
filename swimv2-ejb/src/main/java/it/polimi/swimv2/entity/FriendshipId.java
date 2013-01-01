package it.polimi.swimv2.entity;

import java.io.Serializable;

public class FriendshipId implements Serializable {
	
	private static final long serialVersionUID = 6684163541032025055L;

	/* fields referencing User primary keys */
	private int user1;
	private int user2;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + user1;
		result = prime * result + user2;
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
		if (user1 != other.user1)
			return false;
		if (user2 != other.user2)
			return false;
		return true;
	}
	
}
