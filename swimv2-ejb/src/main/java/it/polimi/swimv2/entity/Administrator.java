package it.polimi.swimv2.entity;

import it.polimi.swimv2.entity.User;
import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Administrator extends User implements Serializable {

	private static final long serialVersionUID = 1L;

	public Administrator() {
		super();
	}
	
	@Override
	public boolean isAdmin() {
		return true;
	}

}
