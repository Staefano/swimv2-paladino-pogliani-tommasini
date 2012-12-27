package it.polimi.swimv2.enums;

public enum Role {
	
	
	helper("helper"), asker("asker");

	String role;
	private Role(String role){
		this.role=role;
	}
}
