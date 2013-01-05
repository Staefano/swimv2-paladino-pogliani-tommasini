

import it.polimi.swimv2.session.AuthenticationBeanRemote;
import it.polimi.swimv2.session.exceptions.NotUniqueException;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InserisciUtente
 */
public class InserisciUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;   
	
	@EJB
	AuthenticationBeanRemote bean;

    public InserisciUtente() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			bean.register("pogliamarci@hotmail.it", "aaa");
		} catch (NotUniqueException e) {
			throw new ServletException();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
