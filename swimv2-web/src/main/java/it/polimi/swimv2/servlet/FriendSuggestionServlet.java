package it.polimi.swimv2.servlet;

import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.exceptions.NoSuchUserException;
import it.polimi.swimv2.session.remote.FriendShipBeanRemote;
import it.polimi.swimv2.session.remote.UserBeanRemote;
import it.polimi.swimv2.webutils.AccessRole;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;

/**
 * Servlet implementation class FriendSuggestionServlet
 */
public class FriendSuggestionServlet extends Controller {
	private static final long serialVersionUID = 1L;
       
	@EJB
	private UserBeanRemote userBean;
	
	@EJB
	private FriendShipBeanRemote frshpBean;

    public FriendSuggestionServlet() {
        super(AccessRole.USER);
    }

	@Override
	protected void get(Navigation nav) throws IOException, ServletException {
		String userId  = nav.getParam("id");
		User u;
		try {
			u = userBean.getUserByID(Integer.parseInt(userId));
			List<User> friends = frshpBean.getFriends(u);
			nav.setAttribute("friends", friends);
			nav.fwd("WEB-INF/friendsuggestions.jsp");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
