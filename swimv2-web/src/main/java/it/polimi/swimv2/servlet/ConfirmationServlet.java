package it.polimi.swimv2.servlet;

import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.AuthenticationBeanRemote;
import it.polimi.swimv2.session.exceptions.NoSuchUserException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConfirmationServlet extends HttpServlet {

	private static final long serialVersionUID = 99537518690284192L;

	private static final String COMPLETEREG_JSP = "/WEB-INF/complete-registration.jsp";
	
	@EJB
	private AuthenticationBeanRemote auth;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// validate the form and create the user. then redirect to the personalpage
		// otherwise display an error and go back to this page
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String token = request.getParameter("token");
		
		try {
			// draft!
			User user = new User();
			user.setName(name);
			user.setSurname(surname);
			user = auth.completeRegistration(token, user);
			// login the user!
			request.getSession().setAttribute("user",  user);
			response.sendRedirect(request.getContextPath());
		} catch(NoSuchUserException e) {
			request.setAttribute("formError", true);
			request.getRequestDispatcher(COMPLETEREG_JSP).forward(request, response);
		}
		
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String token = request.getParameter("token");

		if (token == null || !auth.checkConfirmCode(token)) {
			request.setAttribute("wrongToken", true);
		} else {
			request.setAttribute("token", token);
		}
		request.getRequestDispatcher(COMPLETEREG_JSP).forward(request, response);
	}
}
