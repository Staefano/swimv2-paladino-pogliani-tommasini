package it.polimi.swimv2.enums;

public enum NotificationType {

	friendshipReceived("fr"), friendshipAccepted("fa"), abilityAccepted("aa");
	
	String type;
	
	private NotificationType(String type){
		this.type=type;
	}
	
	
}
