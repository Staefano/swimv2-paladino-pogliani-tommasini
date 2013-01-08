package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.Message;
import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.exceptions.OperationFailedException;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface MessageManagerBeanRemote {

	/**
	 * Writes a new message between two users that are friends
	 * 
	 * @param from The sender of the message
	 * @param to The recipient of the message
	 * @param text The content of the message
	 * @throws OperationFailedException if from and to are not friends
	 */
	void write(User from, User to, String text) throws OperationFailedException;

	/**
	 * Get a list of messages representing an entire conversation between
	 * two users, sorted by the timestamp of the message. Both messages sent
	 * from current to other are retrieved, and messages are marked as read
	 * inside the database with the point of view of the current user (e.g. 
	 * only messaged sent to current are marked as read)
	 */
	List<Message> getConversation(User current, User other);

	/**
	 * Get a list of users that have a conversation with the specified user
	 * that has non zero messages.
	 */
	List<User> getUsersWithConversations(User current);

}
