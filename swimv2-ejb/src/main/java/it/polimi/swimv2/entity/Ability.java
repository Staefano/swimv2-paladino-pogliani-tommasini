package it.polimi.swimv2.entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

@Entity

public class Ability implements Serializable {
	   
	@Id
	private String name;
	private static final long serialVersionUID = 1L;
	
	
	public Ability() {
		super();
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
		if (this == obj)
			return true;
		if (obj == null || !(obj instanceof Ability))
			return false;
		Ability other = (Ability) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
   
}
