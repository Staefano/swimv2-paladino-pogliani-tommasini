package it.polimi.swimv2.servlet;

import it.polimi.swimv2.webutils.AccessRole;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddComment
 */
public class AddComment extends Controller {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Controller#Controller()
	 */
	public AddComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Controller#Controller(AccessRole)
	 */
	public AddComment(AccessRole role) {
		super(role);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void get(Navigation nav) throws IOException, ServletException {

		String hr_id = nav.getAttribute("hr_id");
		nav.setAttribute("hr", hr_id);
		nav.fwd("WEB-INF/addcomment.jsp");

	}

	@Override
	protected void post(Navigation nav) throws IOException, ServletException {
		// TODO Auto-generated method stub

	}

}
