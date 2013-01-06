package it.polimi.swimv2.webutils;

public enum AccessRole {

	UNREGISTERED, USER, ADMIN;

	public boolean geqThan(AccessRole minimumRole) {
		return (this == ADMIN) || (this == USER) && minimumRole != ADMIN
				|| (this == UNREGISTERED) && minimumRole == UNREGISTERED;
	}
}
