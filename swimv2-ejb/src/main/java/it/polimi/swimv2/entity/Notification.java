package it.polimi.swimv2.entity;

import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.enums.NotificationType;

import java.io.Serializable;
import java.lang.String;
import java.sql.Timestamp;
import javax.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name="Notification.findBytgtUser",
			query="SELECT x FROM Notification x WHERE x.tgtuser = :user")

})

public class Notification implements Serializable {

	@Id
	private int id;
	private Timestamp timestamp;
	private String ability;
	private User user1_id;
	private User user2_id;
	private static final long serialVersionUID = 1L;
	private NotificationType type;

	@ManyToOne
	private User tgtuser;

	public Notification() {
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

	public String getAbility() {
		return this.ability;
	}

	public void setAbility(String ability) {
		this.ability = ability;
	}

	public User getUser2_id() {
		return this.user2_id;
	}

	public void setUser2_id(User user2_id) {
		this.user2_id = user2_id;
	}

	public NotificationType getType() {
		return type;
	}

	public void setType(NotificationType type) {
		this.type = type;
	}
	public User getUser1_id() {
		return user1_id;
	}
	public void setUser1_id(User user1_id) {
		this.user1_id = user1_id;
	}
   

	public User getTgtuser() {
		return tgtuser;
	}

	public void setTgtuser(User tgtuser) {
		this.tgtuser = tgtuser;
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Notification))
			return false;
		Notification other = (Notification) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", timestamp=" + timestamp
				+ ", ability=" + ability + ", user2_id=" + user2_id + ", type="
				+ type + ", tgtuser=" + tgtuser + "]";
	}

}
