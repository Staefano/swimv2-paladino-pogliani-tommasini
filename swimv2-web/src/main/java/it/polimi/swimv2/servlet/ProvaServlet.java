package it.polimi.swimv2.servlet;

import it.polimi.swimv2.session.ProvaBeanRemote;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ProvaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		
		
		ProvaBeanRemote bean = null;
		try {
			InitialContext context = new InitialContext();
			bean = (ProvaBeanRemote) context.lookup("/swimv2-ear/ProvaBean/remote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		 
		request.setAttribute("hello", bean.getHello());
		
		request.getRequestDispatcher("/WEB-INF/hello.jsp").forward(request, response);
		
	}

}
