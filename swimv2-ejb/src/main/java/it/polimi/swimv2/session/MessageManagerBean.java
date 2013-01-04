package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.Message;
import it.polimi.swimv2.entity.User;

import java.util.List;

import javax.ejb.Stateless;

@Stateless
public class MessageManagerBean implements MessageManagerBeanRemote {
	
	public void write(User from, User to, String text) {
		
	}
	
	// all the messages or with a limitation???
	public List<Message> getByUsername(User from, User to) {
		return null;
	}
	
	// latest what???
	public List<Message> getLatestUnread(User to) {
		return null;
	}
	

}
