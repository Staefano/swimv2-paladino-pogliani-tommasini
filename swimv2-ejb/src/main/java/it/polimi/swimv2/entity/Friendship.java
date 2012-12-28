package it.polimi.swimv2.entity;

import it.polimi.swimv2.entity.User;
import java.io.Serializable;
import javax.persistence.*;

@Entity @IdClass(FriendshipId.class)

public class Friendship implements Serializable {
	
	@Id	private User user1;
	@Id	private User user2;
	
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
   
}
