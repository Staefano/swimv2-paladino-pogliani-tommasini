package it.polimi.swimv2.entity;

import javax.persistence.Id;

public class PendingFriendship {

	
	@Id
	private int asker;

	@Id	
	private int receiver;
	
	
}
