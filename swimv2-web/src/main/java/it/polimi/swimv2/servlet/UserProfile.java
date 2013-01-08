package it.polimi.swimv2.servlet;

import it.polimi.swimv2.entity.HelpRequest;
import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.UserBeanRemote;
import it.polimi.swimv2.session.exceptions.NoSuchUserException;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	private UserBeanRemote ubr;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	@Override
	protected void get(Navigation nav) throws IOException, ServletException {

		String id = nav.getParam("userId");
		try {

			User u = ubr.getUserByID(Integer.parseInt(id));
			nav.setAttribute("user", u);
			// nav.setAttribute("providedList", ubr.getProvidedHelpRequest(u));
			// nav.setAttribute("receivedList", ubr.getReceivedHelpRequest(u));

			HelpRequest hr1 = new HelpRequest();
			hr1.setSubject("hr1prov");
			HelpRequest hr2 = new HelpRequest();
			hr2.setSubject("hr2prov");
			HelpRequest hr3 = new HelpRequest();
			hr3.setSubject("hr3rec");
			HelpRequest hr4 = new HelpRequest();
			hr4.setSubject("hr4rec");
			List<HelpRequest> provList = new ArrayList<HelpRequest>();
			List<HelpRequest> receivList = new ArrayList<HelpRequest>();
			provList.add(hr1);
			provList.add(hr2);
			receivList.add(hr3);
			receivList.add(hr4);

			nav.setAttribute("providedList", provList);
			nav.setAttribute("receivedList", receivList);

			nav.fwd("WEB-INF/userprofile.jsp");
		} catch (NoSuchUserException nsue) {
			nav.fwd("WEB-INF/error.jsp");

		}
	}


}
