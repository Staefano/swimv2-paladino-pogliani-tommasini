package it.polimi.swimv2.servlet;

import it.polimi.swimv2.entity.AbilityRequest;
import it.polimi.swimv2.session.remote.AbilityBeanRemote;
import it.polimi.swimv2.session.remote.NotificationBeanRemote;
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
	private AbilityBeanRemote abilityBean;
	
	@EJB
	private NotificationBeanRemote notificationBean;

	public AdminServlet() {
		super(AccessRole.ADMIN);
	}

	protected void get(Navigation nav) throws IOException, ServletException {

		// Add new Ability
		String ability = nav.getParam("ability");
		if (isOk(ability)) {
			if (abilityBean.alreadyExist(ability)) {
				nav.setAttribute("message", "already");
				nav.setAttribute("abName", ability);
			} else {
				abilityBean.addNewAbility(ability);
				nav.setAttribute("message", "new");
				nav.setAttribute("abName", ability);
			}
		}

		// Approve or reject AbilityRequest
		String choice = nav.getParam("choice");
		if (choice != null) {
			int abId = Integer.parseInt((String) nav.getParam("abId"));
			AbilityRequest request = abilityBean.getRequest(abId);
			String abName = request.getAbility();

			if (choice.equals("approve")
					&& !(abilityBean.alreadyExist(abName))) {
				abilityBean.addNewAbility(request.getAbility());
			}
			notificationBean.notifyAbilityChoice(request, choice);
			abilityBean.removeAbilityRequest(request);
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
	
	boolean isOk(String ability) {
		if(ability != null) {
			if(ability.equals("")) {
				return false;
			}
			return true;
		}
		return false;
	}

}
