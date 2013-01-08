package it.polimi.swimv2.servlet;

import it.polimi.swimv2.entity.Friendship;
import it.polimi.swimv2.entity.Message;
import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.FriendShipBeanRemote;
import it.polimi.swimv2.session.MessageManagerBeanRemote;
import it.polimi.swimv2.session.UserBeanRemote;
import it.polimi.swimv2.webutils.AccessRole;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;

/**
 * Servlet implementation class MessageServlet
 */
public class MessageServlet extends Controller {
	private static final long serialVersionUID = 1L;
    
	@EJB MessageManagerBeanRemote messages;
	@EJB UserBeanRemote users;
	@EJB FriendShipBeanRemote friends;
	
	private static final String MESSAGES_JSP = "WEB-INF/messages/messages.jsp";
	private static final String CONVERSATION_JSP = "WEB-INF/messages/conversation.jsp"; 
	
    public MessageServlet() {
        super(AccessRole.USER);
    }
    
	@Override
	protected void get(Navigation nav) throws IOException, ServletException {
		String conversation = nav.getParam("conversation");
		try {
			 processSpecificConversation(nav, Integer.parseInt(conversation));
		} catch(NumberFormatException nfe) {
			processConversationList(nav);
		}
	}

	@Override
	protected void post(Navigation nav) throws IOException, ServletException {
		// with post parameters --> send a message (and then return the conversation...)
		// TODO to be done!!!
		nav.fwd(MESSAGES_JSP);
	}
	
	private void processSpecificConversation(Navigation nav, int id) throws IOException, ServletException {
		try {
			// TODO if the user is a friend of the other one, else throw something!!!
			User other = users.getUserByID(id);
			List<Message> messageList = messages.getConversation(nav.getLoggedUser(), other);
			nav.setAttribute("otherUser", other);
			nav.setAttribute("messages", messageList);
			nav.fwd(CONVERSATION_JSP);
		} catch(Exception e) {
			// TODO
		}
	}
	
	private void processConversationList(Navigation nav) throws IOException, ServletException {
		List<User> allFriends = friends.getFriends(nav.getLoggedUser());
		nav.setAttribute("userList",  allFriends);
		nav.fwd(MESSAGES_JSP);		
	}

}
