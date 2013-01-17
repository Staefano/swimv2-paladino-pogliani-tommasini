package it.polimi.swimv2.servlet;

import java.io.IOException;
import java.util.List;

import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.exceptions.NoSuchUserException;
import it.polimi.swimv2.session.remote.HelpRequestRemote;
import it.polimi.swimv2.session.remote.MessageManagerBeanRemote;
import it.polimi.swimv2.session.remote.NotificationBeanRemote;
import it.polimi.swimv2.session.remote.UserBeanRemote;
import it.polimi.swimv2.webutils.AccessRole;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

import javax.ejb.EJB;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

public class PersonalAreaServlet extends Controller implements Servlet {

	private static final long serialVersionUID = -3222571872656785471L;

	private static final String PERSONALAREA_JSP = "/WEB-INF/personalarea.jsp";

	@EJB
	private MessageManagerBeanRemote messageBean;
	
	@EJB
	private NotificationBeanRemote notificationBean;
	
	@EJB
	private HelpRequestRemote hrBean;

	@EJB
	private UserBeanRemote userBean;
	
    public PersonalAreaServlet() {
        super(AccessRole.USER);
    }
	
	@Override
	protected void get(Navigation nav) throws IOException, ServletException {
		// Here we set all the areas in the user page...
		// Message area
		User loggedUser = nav.getLoggedUser();
		List<User> usersWithUnread = messageBean.getUnreadConversations(loggedUser);
		nav.setAttribute("notifications", notificationBean.getNotifications(loggedUser));
		nav.setAttribute("usersWithUnread",  usersWithUnread);
		nav.setAttribute("openProvidingHR", hrBean.getOpenProvidedHR(loggedUser));
		nav.setAttribute("openReceivingHR", hrBean.getOpenRequestedHR(loggedUser));
		
		try {
			User logged = nav.getLoggedUser();
			nav.setLogin(userBean.getUserByID(logged.getId()));
		} catch (NoSuchUserException e) {
		}
		
		nav.setAttribute("xp", loggedUser.getExperience());
		nav.setAttribute("rep", loggedUser.getReputation());

		nav.fwd(PERSONALAREA_JSP);
	}

}
