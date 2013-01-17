package it.polimi.swimv2.servlet;

import it.polimi.swimv2.enums.FeedbackValue;
import it.polimi.swimv2.session.exceptions.ClosedHelpRequestException;
import it.polimi.swimv2.session.exceptions.NoSouchHRException;
import it.polimi.swimv2.session.remote.HelpRequestRemote;
import it.polimi.swimv2.webutils.AccessRole;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

public class FeedbackServlet extends Controller implements Servlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private HelpRequestRemote hrBean;
	
	public FeedbackServlet() {
		super(AccessRole.USER);
	}

	@Override
	protected void post(Navigation nav) throws IOException, ServletException {

		String requestId = nav.getParam("hr_id");
		FeedbackValue evaluation = FeedbackValue.stringToValue((String) nav.getParam("evaluation"));
		String comment = nav.getParam("comment");

		try {
			hrBean.addFeedback(
					hrBean.findByID(Integer.parseInt(requestId)),
					evaluation, comment, nav.getLoggedUser());
		} catch (NoSouchHRException e) {
			// TODO Auto-generated catch block
		} catch (ClosedHelpRequestException e) {
			// TODO Auto-generated catch block
		}

		nav.fwd(BASEPATH);

	}

	@Override
	protected void get(Navigation nav) throws IOException, ServletException {

		nav.setAttribute("hr", nav.getParam("hr_id"));
		nav.setAttribute("role", nav.getParam("role"));
		nav.fwd("WEB-INF/feedbackform.jsp");

	}

}
