package it.polimi.swimv2.servlet;

import it.polimi.swimv2.enums.Role;
import it.polimi.swimv2.session.HelpRequestRemote;
import it.polimi.swimv2.session.exceptions.ClosedHelpRequestException;
import it.polimi.swimv2.session.exceptions.NoSouchHRException;
import it.polimi.swimv2.webutils.AccessRole;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

/**
 * Servlet implementation class FeedbackServlet
 */
public class FeedbackServlet extends Controller implements Servlet {
	private static final long serialVersionUID = 1L;
      
	@EJB HelpRequestRemote hrBean;
    /**
     * @see Controller#Controller()
     */
    public FeedbackServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
       
    /**
     * @see Controller#Controller(AccessRole)
     */
    public FeedbackServlet(AccessRole role) {
        super(role);
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void post(Navigation nav) throws IOException, ServletException {
	
			String hr_id = nav.getParam("hr_id");
			String role = nav.getParam("role");
			int evaluation = Integer.parseInt((String) nav.getParam("evaluation"));
			String comment = nav.getParam("comment");
			
			try {
					if(role.equals("helper")){
						hrBean.addFeedbakc(hrBean.findByID(Integer.parseInt(hr_id)), evaluation, comment, Role.HELPER);
					}else if(role.equals("asker")){
						hrBean.addFeedbakc(hrBean.findByID(Integer.parseInt(hr_id)), evaluation, comment, Role.ASKER);

					}

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
