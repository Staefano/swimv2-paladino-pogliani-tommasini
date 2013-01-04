package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.Message;
import it.polimi.swimv2.entity.User;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface MessageManagerBeanRemote {
	public void write(User from, User to, String text);

	public List<Message> getByUsername(User from, User to);

	public List<Message> getLatestUnread(User to);
}
