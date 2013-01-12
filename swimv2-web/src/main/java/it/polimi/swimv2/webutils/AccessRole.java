package it.polimi.swimv2.webutils;

public enum AccessRole {

	UNREGISTERED, USER, ADMIN;

	public boolean geqThan(AccessRole minimumRole) {
		switch(this) {
		case ADMIN:
			return true;
		case USER:
			return minimumRole != ADMIN;
		case UNREGISTERED:
		default:
			return minimumRole == UNREGISTERED;
		}
	}
}
