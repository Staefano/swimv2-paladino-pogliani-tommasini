package it.polimi.swimv2.servlet;

import it.polimi.swimv2.entity.Message;
import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.MessageManagerBeanRemote;
import it.polimi.swimv2.session.UserBeanRemote;
import it.polimi.swimv2.session.exceptions.NoSuchUserException;
import it.polimi.swimv2.session.exceptions.OperationFailedException;
import it.polimi.swimv2.webutils.AccessRole;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;

public class MessageServlet extends Controller {
	private static final long serialVersionUID = 1L;
    
	@EJB MessageManagerBeanRemote messages;
	@EJB UserBeanRemote users;
	
	private static final String MESSAGES_JSP = "WEB-INF/messages/messages.jsp";
	private static final String CONVERSATION_JSP = "WEB-INF/messages/conversation.jsp"; 
	
    public MessageServlet() {
        super(AccessRole.USER);
    }
    
	@Override
	protected void get(Navigation nav) throws IOException, ServletException {
		String conversation = nav.getParam("conversation");
		try {
			int id = Integer.parseInt(conversation);
			processSpecificConversation(nav, users.getUserByID(id));
		} catch(NumberFormatException nfe) {
			processConversationList(nav);
		} catch(NoSuchUserException e) {
			processConversationList(nav);
		}
	}

	@Override
	protected void post(Navigation nav) throws IOException, ServletException {
		String to = nav.getParam("to");
		String text = nav.getParam("text");
		String deleteId = nav.getParam("id");
		if(nav.getParam("delete") != null) {
			processDelete(deleteId, nav);
		} else {
			processSend(to, text, nav);
		}
	}
	
	private void processDelete(String id, Navigation nav) throws ServletException, IOException {
		try {
			int parsedId = Integer.parseInt(id);
			messages.deleteConversation(nav.getLoggedUser(), users.getUserByID(parsedId));
		} catch(NumberFormatException nfe) {
			// ok, go on
		} catch(OperationFailedException ofe) {
			// ok, go on
		} finally { // go to the message page
			processConversationList(nav);
		}
		
	}
	
	private void processSend(String to, String text, Navigation nav) throws ServletException, IOException {
		try {
			int userId = Integer.parseInt(to);
			User other = users.getUserByID(userId);
			messages.write(nav.getLoggedUser(), other, text);
			processSpecificConversation(nav, other);
		} catch(NumberFormatException nfe) {
			processConversationList(nav);
		} catch(OperationFailedException ofe) {
			processConversationList(nav);
		}
	}
	
	private void processSpecificConversation(Navigation nav, User other) throws IOException, ServletException {
		try {
			// TODO if the user is a friend of the other one, else throw something!!!
			List<Message> messageList = messages.getConversation(nav.getLoggedUser(), other);
			nav.setAttribute("otherUser", other);
			nav.setAttribute("messages", messageList);
			nav.fwd(CONVERSATION_JSP);
		} catch(Exception e) {
			throw new ServletException(e);
		}
	}
	
	private void processConversationList(Navigation nav) throws IOException, ServletException {
		List<User> people = messages.getUsersWithConversations(nav.getLoggedUser());
		List<User> unread = messages.getUnreadConversations(nav.getLoggedUser());
		nav.setAttribute("userList", people);
		nav.setAttribute("unread", unread);
		nav.fwd(MESSAGES_JSP);
	}

}
