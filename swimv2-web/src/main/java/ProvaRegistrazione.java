import it.polimi.swimv2.session.AuthenticationBeanRemote;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProvaRegistrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	AuthenticationBeanRemote bean;

    public ProvaRegistrazione() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean res1 = bean.checkConfirmCode(request.getParameter("token"));
		PrintWriter p = new PrintWriter(response.getOutputStream());
		if(res1) p.write("Token " + request.getParameter("token") + " OK\n");
		else p.write("Token " + request.getParameter("token") + " errato\n");
		p.flush();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
