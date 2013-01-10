package it.polimi.swimv2.servlet;

import it.polimi.swimv2.entity.Ability;
import it.polimi.swimv2.entity.HelpRequest;
import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.AbilityBeanRemote;
import it.polimi.swimv2.session.HelpRequestRemote;
import it.polimi.swimv2.session.UserBeanRemote;
import it.polimi.swimv2.session.exceptions.ClosedHelpRequestException;
import it.polimi.swimv2.session.exceptions.NoSuchUserException;
import it.polimi.swimv2.webutils.AccessRole;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

import java.io.IOException;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

/**
 * Servlet implementation class HelpRequestServlet
 */
public class HelpRequestServlet extends Controller implements Servlet {
	private static final long serialVersionUID = 1L;

	@EJB
	UserBeanRemote userBean;
	@EJB
	private HelpRequestRemote hrBean;
	@EJB
	private AbilityBeanRemote abilityBean;

	/**
	 * @see Controller#Controller()
	 */
	public HelpRequestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Controller#Controller(AccessRole)
	 */
	public HelpRequestServlet(AccessRole role) {
		super(role);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void get(Navigation nav) throws IOException, ServletException {

		String id = nav.getParam("receiver");
		try {
			User receiver = userBean.getUserByID(Integer.parseInt(id));
			Set<Ability> abilities = (Set<Ability>) receiver.getAbilities();
			nav.setAttribute("receiver", receiver);
			nav.setAttribute("abilities", abilities);

		} catch (NoSuchUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		nav.fwd("WEB-INF/helprequestform.jsp");

	}

	@Override
	protected void post(Navigation nav) throws IOException, ServletException{

		try {
		
			String subject = nav.getParam("subject");
			String[] abilities = nav.getParamValues("ability");
			String comment = nav.getParam("comment");
			
			
			User receiver = userBean.getUserByID(Integer.parseInt((String) nav.getParam("receiver")));
			if(!(receiver.equals(nav.getLoggedUser()))){
				HelpRequest hr = hrBean.askForHelp(nav.getLoggedUser(), receiver, subject, abilityBean.getAbilities(abilities));
			
				
			
			}

			nav.fwd(BASEPATH);
		
		
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
