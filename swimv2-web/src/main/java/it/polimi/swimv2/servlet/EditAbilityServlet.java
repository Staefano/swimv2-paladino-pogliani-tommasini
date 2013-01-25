package it.polimi.swimv2.servlet;

import it.polimi.swimv2.entity.Ability;
import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.remote.AbilityBeanRemote;
import it.polimi.swimv2.session.remote.UserBeanRemote;
import it.polimi.swimv2.webutils.AccessRole;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;

public class EditAbilityServlet extends Controller {

	private static final long serialVersionUID = 1L;

	private static final String EDITABILITIES_JSP = "/WEB-INF/editabilities.jsp";
	
	@EJB
	private AbilityBeanRemote abilityBean;
	
	@EJB
	private UserBeanRemote userBean;

	public EditAbilityServlet() {
		super(AccessRole.USER);
	}

	@Override
	protected void get(Navigation nav) throws IOException, ServletException {

		User user = nav.getLoggedUser();
		
		String action = nav.getParam("action");
		String chosenAb = nav.getParam("abId");
		

		// add ability
		if (chosenAb != null) {
			if("remove".equals(action)) {
				user = userBean.removeUserAbility(user, chosenAb);
				nav.setAttribute("result", "removed");
			} else {
				user = userBean.addUserAbility(user, chosenAb);
				nav.setAttribute("result", "added");
			}
			nav.setAttribute("chosenAb", chosenAb);
			nav.setLogin(user);
		}
		
		// ability request
		String name = nav.getParam("name");
		String comment = nav.getParam("comment");
		
		if (abilityBean.alreadyExist(name)) {
			nav.setAttribute("message", "failed");
		} else if(name==null || name.equals("")) {
			nav.setAttribute("message", "empty");
		} else {
			abilityBean.requestAbility(name, comment, user);
			nav.setAttribute("message", "success");
		}
		nav.setAttribute("abName", name);
		
		// search results
		String queryString = nav.getParam("searchAb");
		if (queryString == null || queryString.isEmpty()) {
			nav.setAttribute("outcome", "emptyField");
		} else {
			List<Ability> abilities = abilityBean.searchAbility(queryString, user);
			nav.setAttribute("abilitiesList", abilities);
			if (abilities == null || abilities.size() == 0) {
				nav.setAttribute("outcome", "noAbilityFound");
			}
		}

		nav.fwd(EDITABILITIES_JSP);
	}

}
