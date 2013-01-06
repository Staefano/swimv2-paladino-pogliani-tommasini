package it.polimi.swimv2.servlet;

import it.polimi.swimv2.session.AuthenticationBeanRemote;
import it.polimi.swimv2.session.exceptions.NoSuchUserException;
import it.polimi.swimv2.session.exceptions.NotUniqueException;
import it.polimi.swimv2.webutils.AccessRole;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Servlet to manage requests to home page (basic requests plus login)
 */
public class HomeServlet extends Controller {

	private static final long serialVersionUID = 9004320551305682450L;

	private static final String INDEX_JSP = "/WEB-INF/index.jsp";
	private static final String PERSONALAREA_JSP = "/WEB-INF/personalarea.jsp";

	@EJB
	private AuthenticationBeanRemote auth;

	@Override
	protected void post(Navigation nav) throws IOException, ServletException {
		// which form has been sent? login => user != null, registration =>
		// email != null
		String user = nav.getParam("user");
		String email = nav.getParam("email");
		String password = nav.getParam("password");

		if (user != null) {
			processLogin(user, password, nav);
		} else if (email != null) {
			processRegistration(email, password, nav);
		} else {
			get(nav);
		}

	}

	@Override
	protected void get(Navigation nav) throws IOException, ServletException {
		if (nav.getRole() != AccessRole.UNREGISTERED) {
			nav.fwd(PERSONALAREA_JSP);
		} else {
			nav.fwd(INDEX_JSP);
		}
	}

	private void processLogin(String user, String password, Navigation nav)
			throws IOException, ServletException {
		if (password == null) {
			nav.setAttribute("wrongLogin", true);
			nav.fwd(INDEX_JSP);
		}
		try {
			nav.setLogin(auth.checkCredentials(user, password));
			nav.fwd(PERSONALAREA_JSP);
		} catch (NoSuchUserException nsue) {
			nav.setAttribute("wrongLogin", true);
			nav.fwd(INDEX_JSP);
		}
	}

	private void processRegistration(String user, String password,
			Navigation nav) throws IOException, ServletException {
		nav.setAttribute("toggleRegistration", true);
		if (password == null) {
			nav.setAttribute("formNotCompleted", true);
			return;
		}

		try {
			auth.register(user, password);
		} catch (NotUniqueException e) {
			nav.setAttribute("alreadyRegistered", true);
		}
		nav.fwd(INDEX_JSP);
	}
}
