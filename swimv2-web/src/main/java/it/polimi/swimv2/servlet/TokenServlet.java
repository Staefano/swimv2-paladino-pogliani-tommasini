package it.polimi.swimv2.servlet;

import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.exceptions.NoSuchUserException;
import it.polimi.swimv2.session.remote.AuthenticationBeanRemote;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import java.io.IOException;

public class TokenServlet extends Controller {

	private static final long serialVersionUID = 99537518690284192L;

	private static final String COMPLETEREG_JSP = "/WEB-INF/tokenvalidation.jsp";
	
	@EJB
	private AuthenticationBeanRemote auth;

	protected void post(Navigation nav) throws ServletException, IOException {
		String password = nav.getParam("password");
		if(password == null) {
			confirmRegistration(nav);
		} else {
			resetPassword(password, nav);
		}
	}
	
	private void confirmRegistration(Navigation nav) throws ServletException, IOException {
		String name = nav.getParam("name");
		String surname = nav.getParam("surname");
		String token = nav.getParam("token");
		
		try {
			User user = new User();
			user.setName(name);
			user.setSurname(surname);
			user = auth.completeRegistration(token, user);
			// login the user!
			nav.setLogin(user);
			nav.redirect("/editprofile");
		} catch(NoSuchUserException e) {
			nav.setAttribute("formError", true);
			nav.fwd(COMPLETEREG_JSP);
		}
	}
	
	private void resetPassword(String password, Navigation nav) throws ServletException, IOException {
		String token = nav.getParam("token");
		try {
			User user = auth.resetPassword(password, token);
			nav.setLogin(user);
			nav.redirect("/");
		} catch (NoSuchUserException e) {
			nav.setAttribute("formError", true);
			nav.fwd(COMPLETEREG_JSP);
		}
		
	}

	protected void get(Navigation nav) throws ServletException, IOException {
		String token = nav.getParam("token");
		nav.setAttribute("tokenOutcome", auth.checkConfirmCode(token));
		nav.setAttribute("token", token);
		nav.fwd(COMPLETEREG_JSP);
		
	}
}
