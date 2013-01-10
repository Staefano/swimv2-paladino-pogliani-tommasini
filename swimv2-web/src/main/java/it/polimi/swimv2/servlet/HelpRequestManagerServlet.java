package it.polimi.swimv2.servlet;

import it.polimi.swimv2.session.HelpRequestRemote;
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
      
	@EJB HelpRequestRemote hrBean;
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
		String hr_id= nav.getParam("hr_id");
		
		try {
			if(choice.equals("approved")){
				hrBean.acceptHR(hrBean.findByID(Integer.parseInt(hr_id)));
				
			}else if(choice.equals("refused")){
				
				hrBean.refuseHR(hrBean.findByID(Integer.parseInt(hr_id)));
				
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
