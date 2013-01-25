package it.polimi.swimv2.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;

import it.polimi.swimv2.session.remote.FriendShipBeanRemote;
import it.polimi.swimv2.session.remote.NotificationBeanRemote;
import it.polimi.swimv2.webutils.AccessRole;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

public class FriendshipServlet extends Controller {
	private static final long serialVersionUID = 1L;

	@EJB 
	private NotificationBeanRemote notificationBean;
	
	@EJB 
	private FriendShipBeanRemote friendshipBean;
	
	public FriendshipServlet() {
		super(AccessRole.USER);
	}

	@Override
	protected void get(Navigation nav) throws IOException, ServletException {

		String answer = nav.getParam("answer");
		String notification = nav.getParam("notification_id");
		
		if(notification == null || "".equals(notification)) {
			nav.redirect("/");
		} else {
			if (answer.equals("accepted")) {
				friendshipBean.createFriendship(notification, nav.getLoggedUser());
				notificationBean.notifyFriendshipAccepted(nav.getLoggedUser(), notification);
			} else if (answer.equals("refused")) {
				notificationBean.deleteNotification(notification);
			}
			nav.redirect("/");
		}
	}

}
