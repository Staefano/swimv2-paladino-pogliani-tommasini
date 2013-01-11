package it.polimi.swimv2.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;

import it.polimi.swimv2.session.FriendShipBeanRemote;
import it.polimi.swimv2.session.NotificationBeanRemote;
import it.polimi.swimv2.webutils.AccessRole;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

/**
 * Servlet implementation class FriendshipServlet
 */
public class FriendshipServlet extends Controller {
	private static final long serialVersionUID = 1L;

	@EJB NotificationBeanRemote notificationBean;
	@EJB FriendShipBeanRemote friendshipBean;
	
	/**
	 * @see Controller#Controller()
	 */
	public FriendshipServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Controller#Controller(AccessRole)
	 */
	public FriendshipServlet(AccessRole role) {
		super(role);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void get(Navigation nav) throws IOException, ServletException {

		String answer = nav.getParam("answer");
		String notification = nav.getParam("notification_id");

		if (answer.equals("accepted")) {

			
			friendshipBean.createFriendship(notification, nav.getLoggedUser());
			notificationBean.notifyFriendshipAccepted(nav.getLoggedUser(),notification);
			nav.fwd(BASEPATH);
		
		
		} else if (answer.equals("refused")) {
			
			notificationBean.deleteNotification(notification);
			nav.fwd(BASEPATH);

		}
	}

}
