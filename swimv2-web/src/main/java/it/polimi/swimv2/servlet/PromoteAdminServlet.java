package it.polimi.swimv2.servlet;

import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.exceptions.NoSuchUserException;
import it.polimi.swimv2.session.remote.NotificationBeanRemote;
import it.polimi.swimv2.session.remote.UserBeanRemote;
import it.polimi.swimv2.webutils.AccessRole;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;

public class PromoteAdminServlet extends Controller {

	private static final long serialVersionUID = 1L;

	@EJB
	UserBeanRemote userBean;
	@EJB
	NotificationBeanRemote notificationBean;

	public PromoteAdminServlet() {
		super(AccessRole.ADMIN);
	}

	@Override
	protected void get(Navigation nav) throws IOException, ServletException {

		int userId = Integer.parseInt((String) nav.getParam("profileId"));
		try {
			User user = userBean.getUserByID(userId);
			userBean.promoteAdmin(user);
			notificationBean.notifyAdminPromotion(user);
			nav.setAttribute("message", true);
			nav.setAttribute("profile", user);
		} catch (NoSuchUserException e) {
			nav.setAttribute("message", false);
		}
		nav.fwd("/WEB-INF/promotion.jsp");
	}

}
