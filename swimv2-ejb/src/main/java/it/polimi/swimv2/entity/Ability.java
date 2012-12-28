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
   
}
