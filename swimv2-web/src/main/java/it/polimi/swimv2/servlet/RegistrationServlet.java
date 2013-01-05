package it.polimi.swimv2.servlet;

import it.polimi.swimv2.session.AuthenticationBeanRemote;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = 99537518690284192L;

	@EJB
	private AuthenticationBeanRemote auth;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String token = request.getParameter("token");

		if (token == null || !auth.checkConfirmCode(token)) {

			request.setAttribute("wrongToken", true);

		}
		request.getRequestDispatcher("WEB-INF/complete-registration.jsp")
				.forward(request, response);

	}
}
