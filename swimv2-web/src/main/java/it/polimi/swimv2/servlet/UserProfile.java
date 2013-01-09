package it.polimi.swimv2.servlet;

import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.NotificationBeanRemote;
import it.polimi.swimv2.session.UserBeanRemote;
import it.polimi.swimv2.session.exceptions.NoSuchUserException;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserProfile
 */
public class UserProfile extends Controller {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserProfile() {
		super();
		// TODO Auto-generated constructor stub
	}
	@EJB
	private NotificationBeanRemote nbr;
	
	@EJB
	private UserBeanRemote ubr;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	@Override
	protected void get(Navigation nav) throws IOException, ServletException {
		
		String id = nav.getParam("id");
		try {

			User u = ubr.getUserByID(Integer.parseInt(id));
			nav.setAttribute("profile", u);
			nav.setAttribute("providedList", ubr.getProvidedHelpRequest(u));
			nav.setAttribute("receivedList", ubr.getReceivedHelpRequest(u));
			nav.setAttribute("notifications", nbr.getNotifications("id"));

			nav.fwd("WEB-INF/userprofile.jsp");
		} catch (NoSuchUserException nsue) {
			nav.fwd("WEB-INF/error.jsp");

		}
	}


}
