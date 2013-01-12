package it.polimi.swimv2.entity;

import it.polimi.swimv2.enums.TokenType;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name="PendingToken.findByToken",
		query="SELECT x FROM PendingToken x WHERE x.confirmCode = :token")
})
public class PendingToken implements Serializable {
   
	@Id
	private String email;
	
	@Column(unique=true)
	private String confirmCode;
	
	private TokenType type;
	
	private String passwordHash; // only if type = NEWUSER
	private static final long serialVersionUID = 1L;

	public PendingToken() {
		super();
	}   
	
	public PendingToken(String email, String token, TokenType type) {
		this.email = email;
		this.confirmCode = token;
		this.type = type;
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
	
	public TokenType getType() {
		return this.type;
	}
	
	public void setTokenType(TokenType type) {
		this.type = type;
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
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof PendingToken)) {
			return false;
		}
		PendingToken other = (PendingToken) obj;
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "PendingUser [email=" + email + ", confirmCode=" + confirmCode
				+ ", passwordHash=" + passwordHash + "]";
	}
   
}
