package it.polimi.swimv2.servlet;

import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.FriendShipBeanRemote;
import it.polimi.swimv2.session.NotificationBeanRemote;
import it.polimi.swimv2.session.UserBeanRemote;
import it.polimi.swimv2.session.exceptions.NoSuchUserException;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;

public class UserProfile extends Controller {
	private static final long serialVersionUID = 1L;

	@EJB
	private NotificationBeanRemote nbr;
	
	@EJB
	private UserBeanRemote ubr;
	
	@EJB
	private FriendShipBeanRemote friendshipBean;

	private static final String PROFILE_PUBLIC_JSP = "WEB-INF/public-userprofile.jsp";
	private static final String PROFILE_PRIVATE_JSP = "WEB-INF/userprofile.jsp";
	
	@Override
	protected void get(Navigation nav) throws IOException, ServletException {
		
		String id = nav.getParam("id");
		boolean isLoggedIn = nav.getLoggedUser() != null;
		
		try {
			User u = ubr.getUserByID(Integer.parseInt(id));
			nav.setAttribute("profile", u);
			nav.setAttribute("friendsList", friendshipBean.getFriends(u));
			nav.setAttribute("providedList", ubr.getProvidedHelpRequest(u));
			nav.setAttribute("receivedList", ubr.getReceivedHelpRequest(u));
			
			if(isLoggedIn) {
				boolean friendRequestAllowed = friendshipBean.isRequestAllowed(nav.getLoggedUser(), u);
				nav.setAttribute("showFR", friendRequestAllowed);
				nav.fwd(PROFILE_PRIVATE_JSP);
			} else {
				nav.fwd(PROFILE_PUBLIC_JSP);
			}
		} catch (NoSuchUserException nsue) {
			nav.sendNotFound();
		} catch (NumberFormatException nfe) {
			nav.sendNotFound();
		}
	}
}
