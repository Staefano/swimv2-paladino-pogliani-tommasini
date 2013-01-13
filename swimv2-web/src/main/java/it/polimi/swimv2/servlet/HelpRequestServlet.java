package it.polimi.swimv2.servlet;

import it.polimi.swimv2.entity.Ability;
import it.polimi.swimv2.entity.HelpRequest;
import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.exceptions.ClosedHelpRequestException;
import it.polimi.swimv2.session.exceptions.NoSuchUserException;
import it.polimi.swimv2.session.remote.AbilityBeanRemote;
import it.polimi.swimv2.session.remote.HelpRequestRemote;
import it.polimi.swimv2.session.remote.UserBeanRemote;
import it.polimi.swimv2.webutils.AccessRole;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

public class HelpRequestServlet extends Controller implements Servlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private UserBeanRemote userBean;

	@EJB
	private HelpRequestRemote hrBean;

	@EJB
	private AbilityBeanRemote abilityBean;

	private static final String HRFORM_JSP = "WEB-INF/helprequestform.jsp";

	public HelpRequestServlet() {
		super(AccessRole.USER);
	}

	@Override
	protected void get(Navigation nav) throws IOException, ServletException {

		String id = nav.getParam("receiver");
		try {
			User receiver = userBean.getUserByID(Integer.parseInt(id));
			Set<Ability> abilities = (Set<Ability>) receiver.getAbilities();
			nav.setAttribute("receiver", receiver);
			nav.setAttribute("abilities", abilities);
			nav.fwd(HRFORM_JSP);
		} catch (NoSuchUserException e) {
			nav.sendNotFound(); // 404
		}
	}

	@Override
	protected void post(Navigation nav) throws IOException, ServletException {
		String subject = nav.getParam("subject");
		String receiverForm = nav.getParam("receiver");
		String[] abilitiesForm = nav.getParamValues("ability");
		String comment = nav.getParam("comment");
		User receiver = null;
		if (abilitiesForm == null || subject == null || subject.trim().isEmpty()) {
			nav.setAttribute("error", "mandatoryField");
			get(nav);
		} else {
			try {
				receiver = userBean.getUserByID(Integer.parseInt(receiverForm));
				processForm(subject, receiver, abilitiesForm, comment, nav);
			} catch (NumberFormatException e) {
				error(nav);
			} catch (NoSuchUserException e) {
				error(nav);
			}
		}
	}

	private void processForm(String subject, User receiver, String[] abilities,
			String comment, Navigation nav) throws IOException, ServletException {
		if (receiver.equals(nav.getLoggedUser())) {
			error(nav);
		} else {
			List<Ability> abilitiesList = abilityBean.getAbilities(abilities);
			HelpRequest hr = hrBean.askForHelp(nav.getLoggedUser(), receiver, subject, abilitiesList);
			try {
				hrBean.addComment(hr, comment, nav.getLoggedUser());
			} catch (ClosedHelpRequestException e) {
				Logger.getLogger("").log(Level.SEVERE, "Trying to add comment to a closed help request");
			}
			nav.fwd(BASEPATH);
		}
	}

	private void error(Navigation nav) throws IOException, ServletException {
		nav.setAttribute("error", "serverError");
		get(nav);
	}

}
