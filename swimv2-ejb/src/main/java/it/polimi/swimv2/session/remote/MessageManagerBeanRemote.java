package it.polimi.swimv2.session.remote;

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
	 * Fetch a list of messages representing an entire conversation between
	 * two users, sorted by the timestamp of the message. Both messages sent
	 * from current to other are retrieved, and messages are marked as read
	 * inside the database with the point of view of the current user (e.g. 
	 * only messaged sent to current are marked as read)
	 */
	List<Message> getConversation(User current, User other);

	/**
	 * Fetch a list of users that have a conversation with the specified user
	 * that has non zero messages.
	 */
	List<User> getUsersWithConversations(User current);

	/**
	 * Fetch a list of the conversations targeting the user current with 
	 * unread messages
	 */
	List<User> getUnreadConversations(User current);

	/**
	 * Delete the conversation between current and other from the
	 * conversation available when current navigates the application.
	 * If the conversation is already deleted from the inverse perspective,
	 * the messages are effectively deleted from the database.
	 */
	void deleteConversation(User current, User other);

}
