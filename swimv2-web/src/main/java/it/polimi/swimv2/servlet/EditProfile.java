package it.polimi.swimv2.servlet;

import it.polimi.swimv2.session.UserBeanRemote;
import it.polimi.swimv2.session.exceptions.NoSuchUserException;
import it.polimi.swimv2.webutils.AccessRole;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

/**
 * Servlet implementation class EditProfile
 */
public class EditProfile extends Controller implements Servlet {
	private static final long serialVersionUID = 1L;
      
	@EJB 
	private UserBeanRemote ubr;
    /**
     * @see Controller#Controller()
     */
    public EditProfile() {
        super();
        // TODO Auto-generated constructor stub
    }
       
    /**
     * @see Controller#Controller(AccessRole)
     */
    public EditProfile(AccessRole role) {
        super(role);
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void get(Navigation nav) throws IOException, ServletException {

		nav.fwd("WEB-INF/editprofile.jsp");
		
	}
	
	@Override
	protected void post(Navigation nav) throws IOException, ServletException {
		
		String name = nav.getParam("name");
		System.err.println(name);
		String surname = nav.getParam("surname");
		String website = nav.getParam("website");
		String birthdate = nav.getParam("birthdate"); //TODO gestire la data
		String location = nav.getParam("location");
		String minibio = nav.getParam("minibio");
		String description = nav.getParam("description");
		try {
			nav.setLogin(ubr.editProfile(nav.getLoggedUser(), name, surname, website, birthdate, location, minibio, description));
		} catch (NoSuchUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nav.fwd(BASEPATH);
		
	}


}
