package it.polimi.swimv2.servlet;

import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.enums.NotificationType;
import it.polimi.swimv2.session.exceptions.NoSuchUserException;
import it.polimi.swimv2.session.exceptions.OperationFailedException;
import it.polimi.swimv2.session.remote.FriendShipBeanRemote;
import it.polimi.swimv2.session.remote.NotificationBeanRemote;
import it.polimi.swimv2.session.remote.UserBeanRemote;
import it.polimi.swimv2.webutils.AccessRole;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;

/**
 * Servlet implementation class FriendSuggestionServlet
 */
public class FriendSuggestionServlet extends Controller {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private UserBeanRemote userBean;
	
	@EJB
	private FriendShipBeanRemote friendshipBean;
	
	@EJB
	private NotificationBeanRemote nbr;

	private static final String SUGGESTIONS_JSP = "/WEB-INF/friendsuggestions.jsp";
	
    public FriendSuggestionServlet() {
        super(AccessRole.USER);
    }
    
	@Override
	protected void get(Navigation nav) throws IOException, ServletException {
		
		String userId  = nav.getParam("id");
		if(nav.getParam("error") != null) {
			nav.setAttribute("outcome", "cannotSend");
		}
		if(nav.getParam("success") != null) {
			nav.setAttribute("outcome", "sent");
		}
		User loggedUser = nav.getLoggedUser();
		User u;
		try {
			u = userBean.getUserByID(Integer.parseInt(userId));
			List<User> friends = friendshipBean.getFriends(u);
			List<User> myFriends = friendshipBean.getFriends(nav.getLoggedUser());
			if(friends != null) {
				friends.remove(loggedUser);
				friends.removeAll(myFriends);
			}
			if(friends == null || friends.size() == 0) {
				nav.setAttribute("outcome", "noUserFound");
			}
			nav.setAttribute("usersList", friends);
			nav.fwd(SUGGESTIONS_JSP);
		} catch (NumberFormatException e) {
			nav.sendNotFound();
		} catch (NoSuchUserException e) {
			nav.sendNotFound();
		}		
	}
	
	@Override
	protected void post(Navigation nav) throws IOException, ServletException {
		String userId = nav.getParam("id");
		User loggedUser = nav.getLoggedUser();
		boolean error = false;
		try {
			User receiver = userBean.getUserByID(Integer.parseInt((String) nav.getParam("receiver")));
			nbr.notifyFriendshipRequest(loggedUser, receiver,
					NotificationType.FRIENDSHIP_RECEIVED);
		} catch (NumberFormatException e) {
			error = true;
		} catch (NoSuchUserException e) {
			error = true;
		} catch (OperationFailedException e) {
			error = true;
		}
		nav.redirect("/friendsuggestions?id=" + userId + (error ? "&error=true" : "&success=true"));
	}

}
