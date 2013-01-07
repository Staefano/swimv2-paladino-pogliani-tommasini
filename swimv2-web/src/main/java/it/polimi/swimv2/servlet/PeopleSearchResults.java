package it.polimi.swimv2.servlet;

import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.UserBean;
import it.polimi.swimv2.session.exceptions.NoSuchUserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PeopleSearchResults
 */
public class PeopleSearchResults extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UserBean userBean = new UserBean();
	
    public PeopleSearchResults() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		
		try {
			List<User> users = userBean.searchUser(name, surname);
			request.setAttribute("usersList", users);
		} catch (NoSuchUserException e) {
			request.setAttribute("No users found!", "message");
		}
		*/
		
		//in mancanza di un db popolato... provo a vedere se la pagina si vede!
		List<User> users = new ArrayList<User>();
		User user = new User();
		user.setName("Nome");
		user.setSurname("Cognome");
		users.add(user);
			
		request.setAttribute("usersList", users);
		
		request.getRequestDispatcher("/WEB-INF/peoplesearchresults.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
