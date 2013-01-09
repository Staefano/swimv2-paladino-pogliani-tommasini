package it.polimi.swimv2.entity;

import it.polimi.swimv2.entity.User;
import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity
@NamedQueries({
		@NamedQuery(name = "Friendship.getByUser", query = "SELECT x FROM Friendship x WHERE x.user1 = :user OR x.user2 = :user"),
		@NamedQuery(name = "Friendship.isFriend", query = "SELECT x FROM Friendship x WHERE (x.user1 = :user1 AND x.user2 = :user2) OR (x.user1 = :user2 AND x.user2 = :user1)")
})
@IdClass(FriendshipId.class)
public class Friendship implements Serializable {

	@Id
	private int user1Id;

	@Id	
	private int user2Id;

	@ManyToOne 
	@JoinColumn(name = "user1")
	private User user1;

	@ManyToOne 
	@JoinColumn(name = "user2")
	private User user2;

	private boolean isDirect;
	
	
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
		this.user2= user2;
	}

	public int getUser1Id() {
		return user1Id;
	}

	public void setUser1Id(int user1Id) {
		this.user1Id = user1Id;
	}

	public int getUser2Id() {
		return user2Id;
	}

	public void setUser2Id(int user2Id) {
		this.user2Id = user2Id;
	}

	public boolean isDirect() {
		return this.isDirect;
	}

	public void setDirect(boolean isDirect) {
		this.isDirect = isDirect;
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
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Friendship)) {
			return false;
		}
		Friendship other = (Friendship) obj;
		if (user1 == null) {
			if(other.user1 != null) {
				return false;
			}
		} else if (!user1.equals(other.user1)) {
			return false;
		}
		if (user2 == null) {
			if (other.user2 != null) {
				return false;
			}
		} else if (!user2.equals(other.user2)) {
			return false;
		}			
		return true;
	}

	@Override
	public String toString() {
		return "Friendship [user1=" + user1Id + ", user2=" + user2
				+ ", is_direct=" + isDirect + "]";
	}


}
