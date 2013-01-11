package it.polimi.swimv2.servlet;

import it.polimi.swimv2.session.exceptions.NoResultFoundException;
import it.polimi.swimv2.session.remote.UserImageBeanRemote;

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
	UserImageBeanRemote bean;
	
	private static final String FALLBACK_PATH = "/img/unknown-profile.jpg";
	private static final String THIS_ADDRESS = "/images/profile";
	private static final String MIMETYPE = "image/png";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");
		try {
			if(user != null) {
				int imageId = bean.getImageIdByUserId(Integer.parseInt(user));
				response.sendRedirect(request.getContextPath() + THIS_ADDRESS + "?image=" + imageId);
			} else {
				// response.setHeader("Cache-Control", "max-age=3600, must-revalidate"); 
				// GRRR Bug in Chrome, it caches the response as a response to the original redirect, so if profile image is changed, it don't get updated...
				// need to either work around this or use a unique ID in generated pages (eg user.image.id instead of user.id) TODO
				int id = Integer.parseInt(request.getParameter("image"));
				byte[] image = bean.getImage(id);
				response.setContentType(MIMETYPE);
				OutputStream output = response.getOutputStream();
				output.write(image);
			}
		} catch(NumberFormatException nfe) {
			fallback(request, response);
		} catch (NoResultFoundException e) {
			fallback(request, response);
		}
	}
	
	private void fallback(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		request.getRequestDispatcher(FALLBACK_PATH).forward(request,  response); // can't cache due to a bug in Chrome...
	}

}
