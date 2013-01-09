package it.polimi.swimv2.servlet;

import it.polimi.swimv2.session.NotificationBeanRemote;
import it.polimi.swimv2.webutils.AccessRole;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;

/**
 * Servlet implementation class FriendshipRefused
 */
public class FriendshipRefused extends Controller {
	private static final long serialVersionUID = 1L;
	@EJB
	NotificationBeanRemote notificationBean;

	/**
	 * @see Controller#Controller()
	 */
	public FriendshipRefused() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Controller#Controller(AccessRole)
	 */
	public FriendshipRefused(AccessRole role) {
		super(role);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void get(Navigation nav) throws IOException, ServletException {
		String notification = nav.getParam("notification_id");
		notificationBean.deleteNotification(notification);
		nav.fwd(BASEPATH);
	}

}
