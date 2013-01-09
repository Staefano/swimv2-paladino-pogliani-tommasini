package it.polimi.swimv2.servlet;

import it.polimi.swimv2.entity.AbilityRequest;
import it.polimi.swimv2.session.AbilityBeanRemote;
import it.polimi.swimv2.webutils.AccessRole;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;

public class AdminServlet extends Controller {

	private static final long serialVersionUID = 1L;

	@EJB
	AbilityBeanRemote abilityBean;

	public AdminServlet() {
		super(AccessRole.ADMIN);
	}

	protected void get(Navigation nav) throws IOException, ServletException {

		// Add new Ability
		String ability = nav.getParam("ability");
		if (ability != null) {
			abilityBean.addNewAbility(ability);
			nav.setAttribute("message", "new");
			nav.setAttribute("abName", ability);
		}

		// Approve or reject AbilityRequest
		String choice = nav.getParam("choice");
		if (choice != null) {
			int abId = Integer.parseInt((String) nav.getParam("abId"));
			AbilityRequest request = abilityBean.getRequest(abId);
			String abName = request.getAbility();

			if (choice.equals("approve")) {
				abilityBean.addNewAbility(request.getAbility());
			}
			abilityBean.removeAbilityRequest(request, choice);
			nav.setAttribute("message", choice);
			nav.setAttribute("abName", abName);
		}

		// Show pending AbilityRequest list
		List<AbilityRequest> results = abilityBean.retrievePendingRequests();
		if (results == null || results.size() == 0) {
			nav.setAttribute("found", false);
		} else {
			nav.setAttribute("found", true);
			nav.setAttribute("results", results);
		}
		nav.fwd("/WEB-INF/admin.jsp");
	}

}
