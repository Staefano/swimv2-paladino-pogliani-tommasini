package it.polimi.swimv2.servlet;

import it.polimi.swimv2.session.FriendShipBeanRemote;
import it.polimi.swimv2.session.NotificationBeanRemote;
import it.polimi.swimv2.webutils.AccessRole;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

/**
 * Servlet implementation class FriendshipAccepted
 */
public class FriendshipAccepted extends Controller implements Servlet {
	private static final long serialVersionUID = 1L;
       
	@EJB NotificationBeanRemote notificationBean;
	@EJB FriendShipBeanRemote friendshipBean;
    /**
     * @see Controller#Controller()
     */
    public FriendshipAccepted() {
        super();
        // TODO Auto-generated constructor stub
    }
       
    /**
     * @see Controller#Controller(AccessRole)
     */
    public FriendshipAccepted(AccessRole role) {
        super(role);
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void get(Navigation nav) throws IOException, ServletException {

		
		String notification = nav.getParam("notification_id");

			
			friendshipBean.createFriendship(notification, nav.getLoggedUser());
			notificationBean.notifyFriendshipAccepted(nav.getLoggedUser(), notification);
			nav.fwd(BASEPATH);
	}

}
