package it.polimi.swimv2.entity;

import java.io.Serializable;
import java.lang.String;
import java.sql.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
@NamedQueries({
	@NamedQuery(name="User.findByEmail", 
			query="SELECT x FROM User x WHERE x.email = :email")
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User implements Serializable {

	@Id
	private int id;
	private String name;
	private String surname;
	
	@Column(unique=true)
	private String email;
	
	private String passwordHash;
	private Date birthdate;
	private String website;
	private String location;
	private String minibio;
	private String description;
	private static final long serialVersionUID = 1L;

	// manytomany unidirezionale dato che ability non tiene traccia dell'user
	@ManyToMany
	@JoinTable(name = "UserAbility",
		joinColumns = { @JoinColumn(name = "user") },
		inverseJoinColumns = { @JoinColumn(name = "ability") } )
	private Set<Ability> abilities;
	
	public User() {
		super();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return this.passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public Date getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getWebsite() {
		return this.website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getMinibio() {
		return this.minibio;
	}

	public void setMinibio(String minibio) {
		this.minibio = minibio;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public boolean isAdmin() {
		return false;
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
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname
				+ ", email=" + email + ", passwordHash=" + passwordHash
				+ ", birthdate=" + birthdate + ", website=" + website
				+ ", location=" + location + ", minibio=" + minibio
				+ ", description=" + description + "]";
	}

}
