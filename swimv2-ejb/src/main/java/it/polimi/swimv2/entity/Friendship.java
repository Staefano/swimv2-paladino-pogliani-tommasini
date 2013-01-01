package it.polimi.swimv2.entity;

import it.polimi.swimv2.entity.User;
import java.io.Serializable;
import javax.persistence.*;

@Entity @IdClass(FriendshipId.class)
public class Friendship implements Serializable {
	
	@ManyToOne @Id 
	private User user1;
	
	@ManyToOne @Id 
	private User user2;
	
	private boolean is_direct;
	private static final long serialVersionUID = 1L;

	
	public Friendship() {
		super();
	}   
	public User getUser1() {
		return this.user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}   
	public User getUser2() {
		return this.user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}   
	public boolean getIs_direct() {
		return this.is_direct;
	}

	public void setIs_direct(boolean is_direct) {
		this.is_direct = is_direct;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user1 == null) ? 0 : user1.hashCode());
		result = prime * result + ((user2 == null) ? 0 : user2.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Friendship))
			return false;
		Friendship other = (Friendship) obj;
		if (user1 == null) {
			if (other.user1 != null)
				return false;
		} else if (!user1.equals(other.user1))
			return false;
		if (user2 == null) {
			if (other.user2 != null)
				return false;
		} else if (!user2.equals(other.user2))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Friendship [user1=" + user1 + ", user2=" + user2
				+ ", is_direct=" + is_direct + "]";
	}
	
	
   
}
