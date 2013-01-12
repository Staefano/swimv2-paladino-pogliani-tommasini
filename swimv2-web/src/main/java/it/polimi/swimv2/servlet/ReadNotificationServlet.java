package it.polimi.swimv2.servlet;

import it.polimi.swimv2.session.remote.NotificationBeanRemote;
import it.polimi.swimv2.webutils.AccessRole;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;

/**
 * Servlet implementation class ReadNotification
 */
public class ReadNotificationServlet extends Controller {
	private static final long serialVersionUID = 1L;
      
	@EJB 
	private NotificationBeanRemote notificationBean;

    public ReadNotificationServlet() {
        super(AccessRole.USER);
    }

	@Override
	protected void get(Navigation nav) throws IOException, ServletException {
		
		notificationBean.deleteNotification((String) nav.getParam("notification_id"));
		nav.fwd(BASEPATH);
	}
}
	