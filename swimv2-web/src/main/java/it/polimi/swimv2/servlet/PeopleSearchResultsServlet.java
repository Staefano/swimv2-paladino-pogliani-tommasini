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

	private static final int PAGESIZE = 5;
	
	@EJB
	private UserBeanRemote userBean;

	public PeopleSearchResultsServlet() {
		super(AccessRole.USER);
	}

	@Override
	protected void get(Navigation nav) throws IOException, ServletException {
		String queryString = nav.getParam("search");
		int pageNumber;
		try {
			pageNumber = Integer.parseInt((String) nav.getParam("page"));
		} catch(NumberFormatException e) {
			pageNumber = 1;
		}		
		if (queryString == null || queryString.isEmpty()) {
			nav.setAttribute("outcome", "emptyField");
		} else {
			List<User> users = userBean.searchUser(queryString, pageNumber, PAGESIZE);
			nav.setAttribute("usersList", users);
			if(users == null || users.size() == 0) {
				nav.setAttribute("outcome", "noUserFound");
			}
			long resultsNumber = userBean.countSearchUser(queryString);
			long pageMax = resultsNumber / PAGESIZE + (resultsNumber % PAGESIZE > 0 ? 1 : 0);
			nav.setAttribute("pageMax", pageMax);
			nav.setAttribute("curPage", pageNumber);
		}
		nav.fwd("/WEB-INF/peoplesearchresults.jsp");
	}

}
