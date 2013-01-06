package it.polimi.swimv2.servlet;

import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.AuthenticationBean;
import it.polimi.swimv2.session.UserBean;
import it.polimi.swimv2.session.exceptions.NoSuchUserException;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserProfile
 */
public class UserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("userId");
		UserBean userBean = new UserBean();
		try{
			User u = userBean.getUserByID(id); 
		}catch (NoSuchUserException nsue){
			request.getRequestDispatcher("WEB-INF/error.jsp").forward(
					request, response);

		}
		
		//TODO
		User tmpUser = new User();
		tmpUser.setName("riccardo");
		tmpUser.setSurname("tommasini");
		tmpUser.setPasswordHash("nigeria");
		tmpUser.setEmail("tomma156@gmail.com");

		//request.setAttribute("name", tmpUser.getName());
		request.setAttribute("surname", tmpUser.getSurname());

		//request.getRequestDispatcher("WEB-INF/userprofile.jsp").forward(	request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
