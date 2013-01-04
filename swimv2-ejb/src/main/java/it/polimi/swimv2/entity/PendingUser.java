package it.polimi.swimv2.entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name="PendingUser.findByToken",
		query="SELECT x FROM PendingUser x WHERE x.confirmCode = :token")
})
public class PendingUser implements Serializable {
   
	@Id
	private String email;
	
	@Column(unique=true) 
	private String confirmCode;
	
	private String passwordHash;
	private static final long serialVersionUID = 1L;

	public PendingUser() {
		super();
	}   
	
	public PendingUser(String email, String hash, String token) {
		this.email = email;
		this.passwordHash = hash;
		this.confirmCode = token;
	}
	
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getConfirmCode() {
		return this.confirmCode;
	}

	public void setConfirmCode(String confirmCode) {
		this.confirmCode = confirmCode;
	}   
	public String getPasswordHash() {
		return this.passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof PendingUser))
			return false;
		PendingUser other = (PendingUser) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "PendingUser [email=" + email + ", confirmCode=" + confirmCode
				+ ", passwordHash=" + passwordHash + "]";
	}
   
}
