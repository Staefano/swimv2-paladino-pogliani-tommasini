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
    
    private static final String COMMENTS_JSP = "WEB-INF/comments.jsp";
    
	@Override
	protected void get(Navigation nav) throws IOException, ServletException {
		String requestId = nav.getParam("hr_id");
		try {
			HelpRequest hr = hrBean.findByID(Integer.parseInt(requestId));
			List<it.polimi.swimv2.entity.Comment> comments = hrBean.getComments(hr);
			nav.setAttribute("comments", comments);
			nav.setAttribute("hr", hr);
			nav.setAttribute("isopen", hr.isOpened());
			nav.fwd(COMMENTS_JSP);
		} catch (NumberFormatException e) {
			nav.sendNotFound();
		} catch (NoSouchHRException e) {
			nav.sendNotFound();
		}
	}
	
    
	@Override
	protected void post(Navigation nav) throws IOException, ServletException {
		String requestId = nav.getParam("hr_id");
		try {
			HelpRequest hr = hrBean.findByID(Integer.parseInt(requestId));
			String comment = nav.getParam("comment");
			hrBean.addComment(hr, comment, nav.getLoggedUser());
			nav.redirect("/comment?hr_id=" + requestId);
		} catch (NumberFormatException e) {
			nav.sendNotFound();
		} catch (NoSouchHRException e) {
			nav.sendNotFound();
		} catch (ClosedHelpRequestException e) {
			nav.setAttribute("error", "ClosedRequest");
			nav.redirect("/comment?hr_id=" + requestId);
		}
	}
}
