package it.polimi.swimv2.servlet;

import it.polimi.swimv2.session.AuthenticationBeanRemote;
import it.polimi.swimv2.session.exceptions.NoSuchUserException;
import it.polimi.swimv2.session.exceptions.NotUniqueException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import it.polimi.swimv2.entity.User;

/**
 * Servlet to manage requests to home page (basic requests plus login)
 */
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 9004320551305682450L;
	
	private static final String INDEX_JSP = "/WEB-INF/index.jsp";
	private static final String PERSONALAREA_JSP = "/WEB-INF/personalarea.jsp";
	
	@EJB
    private AuthenticationBeanRemote auth;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
    
    	// which form has been sent? login => user != null, registration => email != null
    	String user = request.getParameter("user");
    	String email = request.getParameter("email");
        String password = request.getParameter("password");

        if(user != null) {
        	processLogin(user, password, request, response);
        } else if(email != null) {
        	processRegistration(email, password, request, response);
        } else {
        	doGet(request, response);
        }
    }
    
    private void processLogin(String user, String password, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if (password == null) {
			request.setAttribute("wrongLogin", true);
			return;
		}
		try {
			User u = auth.checkCredentials(user, password);
			request.getSession().setAttribute("user", u);
			request.getRequestDispatcher(PERSONALAREA_JSP).forward(request, response);
		} catch (NoSuchUserException nsue) {
			request.setAttribute("wrongLogin", true);
			request.getRequestDispatcher(INDEX_JSP).forward(request, response);
		}
	}

	private void processRegistration(String user, String password,
			HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setAttribute("toggleRegistration", true);
		if (password == null) {
			request.setAttribute("formNotCompleted", true);
			return;
		}

		try {
			auth.register(user, password);
		} catch (NotUniqueException e) {
			request.setAttribute("alreadyRegistered", true);
		}
		request.getRequestDispatcher(INDEX_JSP).forward(request, response);
	}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if(request.getSession().getAttribute("user") != null) {
    		request.getRequestDispatcher(PERSONALAREA_JSP).forward(request, response);
    	} else {
    		request.getRequestDispatcher(INDEX_JSP).forward(request, response);
    	}
    }
}
