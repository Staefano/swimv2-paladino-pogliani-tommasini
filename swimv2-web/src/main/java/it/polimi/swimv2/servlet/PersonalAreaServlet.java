package it.polimi.swimv2.servlet;

import java.io.IOException;
import java.util.List;

import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.MessageManagerBeanRemote;
import it.polimi.swimv2.webutils.AccessRole;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

import javax.ejb.EJB;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

public class PersonalAreaServlet extends Controller implements Servlet {

	private static final long serialVersionUID = -3222571872656785471L;

	private static final String PERSONALAREA_JSP = "/WEB-INF/personalarea.jsp";

	@EJB MessageManagerBeanRemote messageBean;

    public PersonalAreaServlet() {
        super(AccessRole.USER);
    }
	
	@Override
	protected void get(Navigation nav) throws IOException, ServletException {
		// Here we set all the areas in the user page...
		// Message area
		List<User> usersWithUnread = messageBean.getUnreadConversations(nav.getLoggedUser());
		nav.setAttribute("usersWithUnread",  usersWithUnread);
		nav.fwd(PERSONALAREA_JSP);
	}

}
