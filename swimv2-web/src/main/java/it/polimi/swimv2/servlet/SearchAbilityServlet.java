package it.polimi.swimv2.servlet;

import it.polimi.swimv2.entity.Ability;
import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.AbilityBeanRemote;
import it.polimi.swimv2.session.UserBeanRemote;
import it.polimi.swimv2.session.exceptions.NoSuchUserException;
import it.polimi.swimv2.webutils.AccessRole;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;

public class SearchAbilityServlet extends Controller {

	private static final long serialVersionUID = 1L;

	@EJB
	AbilityBeanRemote abilityBean;
	@EJB
	UserBeanRemote userBean;

	public SearchAbilityServlet() {
		super(AccessRole.USER);
	}

	@Override
	protected void get(Navigation nav) throws IOException, ServletException {

		User user = nav.getLoggedUser();

		// add ability
		String chosenAb = nav.getParam("abId");
		if (chosenAb != null) {
			int newId = userBean.addUserAbility(user, chosenAb);
			try {
				nav.setLogin(userBean.getUserByID(newId));
				user = nav.getLoggedUser();
			} catch (NoSuchUserException e) {
				//puo' accadere?
			}
			nav.setAttribute("result", "added");
			nav.setAttribute("chosenAb", chosenAb);
		}
		
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
		nav.fwd("/WEB-INF/searchabilityresults.jsp");
	}

}
