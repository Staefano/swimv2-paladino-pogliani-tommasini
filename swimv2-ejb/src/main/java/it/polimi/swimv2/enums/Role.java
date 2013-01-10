package it.polimi.swimv2.enums;

public enum Role {

	HELPER("helper"), ASKER("asker");
	
	private String str;
	private Role(String s) {

		this.str = s;
	}

}
