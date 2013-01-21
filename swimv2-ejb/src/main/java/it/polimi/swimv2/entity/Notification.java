package it.polimi.swimv2.entity;

import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.enums.NotificationType;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.*;

@Entity
@NamedQueries({
		@NamedQuery(name = "Notification.findBytgtUser", query = "SELECT x FROM Notification x WHERE x.tgtUser = :user"),
		@NamedQuery(name = "Notification.findByID", query = "SELECT x FROM Notification x WHERE x.id = :id"),
		@NamedQuery(name = "Notification.isPending", query = "SELECT COUNT(x.id) FROM Notification x " +
				"WHERE (x.srcUser = :user1 AND x.tgtUser =:user2)" +
				"OR (x.srcUser = :user2 AND x.tgtUser =:user1)" + "AND ( (x.type=:direct) OR (x.type=:indirect))" ),


})
public class Notification implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	private int id;
	private Timestamp timestamp;

	private String ability;

	private NotificationType type;

	@ManyToOne
	private User srcUser;

	@ManyToOne
	private User tgtUser;

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
		return timestamp;
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

	public NotificationType getType() {
		return type;
	}

	public void setType(NotificationType type) {
		this.type = type;
	}

	public User getSrcUser() {
		return srcUser;
	}

	public void setSrcUser(User user) {
		this.srcUser = user;
	}

	public User getTgtuser() {
		return tgtUser;
	}

	public void setTgtuser(User tgtuser) {
		this.tgtUser = tgtuser;
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
		if (!(obj instanceof Notification)) {
			return false;
		}
		Notification other = (Notification) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", timestamp=" + timestamp
				+ ", ability=" + ability + ", user2_id=" + srcUser + ", type="
				+ type + ", tgtuser=" + tgtUser + "]";
	}

}
