package it.polimi.swimv2.servlet;

import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

import javax.servlet.ServletException;
import java.io.IOException;

public class LogoutServlet extends Controller {

	private static final long serialVersionUID = 1103591206119674852L;

 	@Override
	protected void get(Navigation nav) throws IOException, ServletException {
		nav.setLogout();
		nav.redirect("/");
		
	}

}
