package it.polimi.swimv2.servlet;

import it.polimi.swimv2.session.ProvaBeanRemote;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddUser
 */
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/add.html").forward(request,  response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		try {
			InitialContext context = new InitialContext();
			ProvaBeanRemote pb = (ProvaBeanRemote) context.lookup("swimv2-ear/ProvaBean/remote");
			String name = request.getParameter("name");
			String surname = request.getParameter("surname");
			if(name != "" && surname != "") {
				pb.addUser(name, surname);
				request.getRequestDispatcher("/view-users").forward(request,  response);
			} else {
				PrintWriter out = new PrintWriter(response.getOutputStream());
				out.write("ERRORE");
			}
		} catch(NamingException e) {
			PrintWriter out = new PrintWriter(response.getOutputStream());
			out.write("ERRORE");
		}
	}

}
