package it.polimi.swimv2.servlet;

import it.polimi.swimv2.entity.HelpRequest;
import it.polimi.swimv2.session.exceptions.ClosedHelpRequestException;
import it.polimi.swimv2.session.exceptions.NoSouchHRException;
import it.polimi.swimv2.session.remote.HelpRequestRemote;
import it.polimi.swimv2.webutils.AccessRole;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;

/**
 * Servlet implementation class Comment
 */
public class CommentServlet extends Controller {
	private static final long serialVersionUID = 1L;
      
	@EJB 
	private HelpRequestRemote hrBean;

    public CommentServlet() {
        super(AccessRole.USER);
    }
    
	@Override
	protected void get(Navigation nav) throws IOException, ServletException {

		String requestId = nav.getParam("hr_id");
		try {
			HelpRequest hr = hrBean.findByID(Integer.parseInt(requestId));
			List<it.polimi.swimv2.entity.Comment> comments = hrBean.getComments(hr);
			nav.setAttribute("comments", comments);
			nav.setAttribute("hr", hr);
			nav.fwd("WEB-INF/comments.jsp");
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSouchHRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
    
	@Override
	protected void post(Navigation nav) throws IOException, ServletException {
		
		String requestId = nav.getParam("hr_id");
		try {
			HelpRequest hr = hrBean.findByID(Integer.parseInt(requestId));
			String comment = nav.getParam("comment");
			hrBean.addComment(hr, comment, nav.getLoggedUser());
			List<it.polimi.swimv2.entity.Comment> comments = hrBean.getComments(hr);
			nav.setAttribute("comments", comments);
			nav.setAttribute("hr", hr);
			nav.fwd("WEB-INF/comments.jsp");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSouchHRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClosedHelpRequestException e) {
			
		}
		
		
	}


}
