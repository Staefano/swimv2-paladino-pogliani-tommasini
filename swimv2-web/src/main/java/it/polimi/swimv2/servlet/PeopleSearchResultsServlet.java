package it.polimi.swimv2.servlet;

import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.remote.UserBeanRemote;
import it.polimi.swimv2.webutils.AccessRole;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;

public class PeopleSearchResultsServlet extends Controller {
	private static final long serialVersionUID = 1L;

	@EJB
	private UserBeanRemote userBean;

	public PeopleSearchResultsServlet() {
		super(AccessRole.USER);
	}

	@Override
	protected void get(Navigation nav) throws IOException, ServletException {
		String queryString = nav.getParam("search");
		if (queryString == null || queryString.isEmpty()) {
			nav.setAttribute("outcome", "emptyField");
		} else {
			List<User> users = userBean.searchUser(queryString);
			nav.setAttribute("usersList", users);
			if(users == null || users.size() == 0) {
				nav.setAttribute("oucome", "noUserFound");
			}
		}
		nav.fwd("/WEB-INF/peoplesearchresults.jsp");
	}

}
