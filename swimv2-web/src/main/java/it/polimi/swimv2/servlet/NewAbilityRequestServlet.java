package it.polimi.swimv2.servlet;

import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.AbilityBeanRemote;
import it.polimi.swimv2.webutils.AccessRole;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;

public class NewAbilityRequestServlet extends Controller {
	
	private static final long serialVersionUID = 1L;

	@EJB
	AbilityBeanRemote abilityBean;

	public NewAbilityRequestServlet() {
		super(AccessRole.USER);
	}

	@Override
	protected void get(Navigation nav) throws IOException, ServletException {
		
		User user = nav.getLoggedUser();
		String name = nav.getParam("name");
		String comment = nav.getParam("comment");
		
		if(abilityBean.alreadyExist(name) || name==null) {
			nav.setAttribute("message", false);
		} else {
			abilityBean.requestAbility(name, comment, user);
			nav.setAttribute("message", true);
		}
		nav.setAttribute("abName", name);
		nav.fwd("/WEB-INF/newabilityrequest.jsp");
	}


}
