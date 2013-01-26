package it.polimi.swimv2.servlet;

import it.polimi.swimv2.session.exceptions.EmailException;
import it.polimi.swimv2.session.exceptions.NoSuchUserException;
import it.polimi.swimv2.session.exceptions.NotUniqueException;
import it.polimi.swimv2.session.remote.AuthenticationBeanRemote;
import it.polimi.swimv2.webutils.AccessRole;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import java.io.IOException;

public class HomeServlet extends Controller {

	private static final long serialVersionUID = 9004320551305682450L;

	private static final String INDEX_JSP = "/WEB-INF/index.jsp";
	
	@EJB
	private AuthenticationBeanRemote auth;

	@Override
	protected void post(Navigation nav) throws IOException, ServletException {
		String user = nav.getParam("user");
		String email = nav.getParam("email");
		String password = nav.getParam("password");

		if (nav.getParam("reset") != null && email != null) {
			processReset(email, nav);
		} else if (user != null) {
			processLogin(user, password, nav);
		} else if (email != null) {
			processRegistration(email, password, nav);
		} else {
			get(nav);
		}

	}

	@Override
	protected void get(Navigation nav) throws IOException, ServletException {
		if(nav.getParam("error") != null && nav.getParam("error").equals("permission")) {
			nav.setAttribute("accessDenied", true);
		}
		if (nav.getRole() != AccessRole.UNREGISTERED) {
			nav.fwd("/personal");
		} else {
			nav.fwd(INDEX_JSP);
		}
	}

	private void processReset(String email, Navigation nav) throws IOException, ServletException {
		String path = nav.getPath();
		try {
			auth.requestPasswordReset(email, path);
			nav.setAttribute("resetRequested", true);
		} catch (NoSuchUserException e) {
			nav.setAttribute("resetFailed", true);
		} catch (EmailException e) {
			nav.setAttribute("resetFailed", true);
		}
		nav.fwd(INDEX_JSP);

	}
	
	private void processLogin(String user, String password, Navigation nav)
			throws IOException, ServletException {
		if (password == null) {
			nav.setAttribute("wrongLogin", true);
			nav.fwd(INDEX_JSP);
		}
		try {
			nav.setLogin(auth.checkCredentials(user, password));
			nav.redirect("/");
		} catch (NoSuchUserException nsue) {
			nav.setAttribute("wrongLogin", true);
			nav.fwd(INDEX_JSP);
		}
	}

	private void processRegistration(String user, String password,
			Navigation nav) throws IOException, ServletException {
		nav.setAttribute("toggleRegistration", true);
		if (password == null) {
			nav.setAttribute("registrationOutcome", 4);
			return;
		}
		try {
			auth.register(user, password, nav.getPath());
			nav.setAttribute("registrationOutcome", 1);
		} catch (NotUniqueException e) {
			nav.setAttribute("registrationOutcome", 3);
		} catch (Exception e) { // TODO this is quite ugly ;)
			nav.setAttribute("registrationOutcome", 2);
		}
		nav.fwd(INDEX_JSP);
	}
}
