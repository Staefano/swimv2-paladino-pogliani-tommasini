package it.polimi.swimv2.servlet;

import it.polimi.swimv2.entity.HelpRequest;
import it.polimi.swimv2.session.HelpRequestRemote;
import it.polimi.swimv2.session.NotificationBeanRemote;
import it.polimi.swimv2.session.exceptions.NoSouchHRException;
import it.polimi.swimv2.webutils.AccessRole;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;

/**
 * Servlet implementation class HelpRequestManagerServlet
 */
public class HelpRequestManagerServlet extends Controller {
	private static final long serialVersionUID = 1L;

	@EJB
	HelpRequestRemote hrBean;
	@EJB
	NotificationBeanRemote notificationBean;

	/**
	 * @see Controller#Controller()
	 */
	public HelpRequestManagerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Controller#Controller(AccessRole)
	 */
	public HelpRequestManagerServlet(AccessRole role) {
		super(role);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void get(Navigation nav) throws IOException, ServletException {

		String choice = nav.getParam("choice");
		String hr_id = nav.getParam("hr_id");

		try {
			HelpRequest hr = hrBean.findByID(Integer.parseInt(hr_id));
			if (choice.equals("approved")) {
				hrBean.acceptHR(hr);

			} else if (choice.equals("refused")) {
				notificationBean.notifyRefusedHelpRe(hr);
				hrBean.refuseHR(hr);
			}

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSouchHRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		nav.fwd(BASEPATH);

	}

}
