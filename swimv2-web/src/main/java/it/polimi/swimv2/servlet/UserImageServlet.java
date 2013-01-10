package it.polimi.swimv2.servlet;

import it.polimi.swimv2.session.UserBeanRemote;
import it.polimi.swimv2.session.exceptions.NoResultFoundException;

import java.io.IOException;
import java.io.OutputStream;

import javax.ejb.EJB;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserImageServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	UserBeanRemote bean;
	
	private static final String FALLBACK_PATH = "/img/unknown-profile.jpg";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("user"));
			byte[] image = bean.getImage(id);
			response.setContentType("image/png"); // TODO get from db?
			OutputStream output = response.getOutputStream();
			output.write(image);
		} catch(NumberFormatException nfe) {
			response.sendRedirect(request.getContextPath() + FALLBACK_PATH);
		} catch (NoResultFoundException e) {
			response.sendRedirect(request.getContextPath() + FALLBACK_PATH);
		}
	}

}
