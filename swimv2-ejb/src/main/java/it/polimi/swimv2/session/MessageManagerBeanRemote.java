package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.Message;
import it.polimi.swimv2.entity.User;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface MessageManagerBeanRemote {
	void write(User from, User to, String text);

	List<Message> getByUsername(User from, User to);

	List<Message> getLatestUnread(User to);
}
