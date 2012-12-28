package it.polimi.swimv2.entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

@Entity

public class PendingUser implements Serializable {
   
	@Id
	private String email;
	private String confirmCode;
	private String passwordHash;
	private static final long serialVersionUID = 1L;

	
	public PendingUser() {
		super();
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
   
}
