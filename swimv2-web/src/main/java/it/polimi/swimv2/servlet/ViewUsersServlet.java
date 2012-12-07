package it.polimi.swimv2.servlet;

import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.ProvaBeanRemote;

import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			InitialContext context = new InitialContext();
			ProvaBeanRemote pb = (ProvaBeanRemote) context
					.lookup("swimv2-ear/ProvaBean/remote");
			List<User> users = pb.getAllUsers();
			request.setAttribute("users",  users);
			request.getRequestDispatcher("/WEB-INF/results.jsp").forward(
					request, response);
		} catch (NamingException e) {
			PrintWriter out = new PrintWriter(response.getOutputStream());
			out.write("ERRORE");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
