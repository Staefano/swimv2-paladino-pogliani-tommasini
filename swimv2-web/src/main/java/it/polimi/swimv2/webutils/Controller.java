package it.polimi.swimv2.webutils;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Base class for swim v2 servlets, containing utilities methods and facilities
 * to manage restricted access to pages. Servlets using these facilities should
 * extend this class and override the get() method and (optionally) the post()
 * method. If the post() method is not overridden, then on POST the get() method
 * is executed. To restrict access to certain user categories, derived classes
 * can declare the minimum role allowed in the minimumRole variable.
 */
public abstract class Controller extends HttpServlet {

	private static final long serialVersionUID = -3211583010566998686L;

	private final AccessRole minimumRole;
	
	protected String BASEPATH = "/";
	protected String ERRORPAGE = "WEB-INF/error.jsp";
	
	public Controller() {
		this(AccessRole.UNREGISTERED);
	}

	public Controller(AccessRole role) {
		this.minimumRole = role;
	}

	protected final void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		act('G', request, response);
		
	}

	protected final void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		act('P', request, response);
	}
	
	private void act(char type, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Navigation nav = new Navigation(request, response);
		if(checkLogin(nav)) {
			if(type == 'G') {
				get(nav);
			} else if(type == 'P') {
				post(nav);
			}
		} else {
			// TODO do something better than redirect to home!
			nav.redirect("/?error=permission");
		}
	}
	
	abstract protected void get(Navigation nav) throws IOException, ServletException;
	
	protected void post(Navigation nav) throws IOException, ServletException {
		get(nav);
	}
	
	private boolean checkLogin(Navigation nav) {
		return nav.getRole().geqThan(minimumRole);
	}
	
	

}
