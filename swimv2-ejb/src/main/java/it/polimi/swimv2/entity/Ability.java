package it.polimi.swimv2.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries({
		@NamedQuery(name = "Ability.searchAbility", query = "SELECT a FROM Ability a WHERE TRIM(a.name) LIKE :name"),
		@NamedQuery(name = "Ability.searchByName", query = "SELECT a FROM Ability a WHERE a.name = :name")
})

public class Ability implements Serializable {
	   
	@Id
	private String name;
	private static final long serialVersionUID = 1L;

	public Ability() {
		
	}
	
	public Ability(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Ability [name=" + name + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		return prime + ((name == null) ? 0 : name.hashCode());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else if (!(obj instanceof Ability)) {
			return false;
		}
		Ability other = (Ability) obj;
		if (name == null) {
			return other.name == null;
		} 
		return name.equals(other.name);
	}
   
}
