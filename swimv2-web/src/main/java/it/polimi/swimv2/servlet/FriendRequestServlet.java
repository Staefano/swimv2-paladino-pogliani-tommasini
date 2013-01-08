package it.polimi.swimv2.servlet;

import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.FriendShipBeanRemote;
import it.polimi.swimv2.session.NotificationBeanRemote;
import it.polimi.swimv2.session.UserBeanRemote;
import it.polimi.swimv2.session.exceptions.NoSuchUserException;
import it.polimi.swimv2.webutils.AccessRole;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;

/**
 * Servlet implementation class FriendRequestServlet
 */
public class FriendRequestServlet extends Controller {
	private static final long serialVersionUID = 1L;
       
	
	@EJB
	private UserBeanRemote ubr;
	@EJB
	private FriendShipBeanRemote fsbr;
	@EJB
	private NotificationBeanRemote nbr;

    /**
     * @see Controller#Controller()
     */
    public FriendRequestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
       
    /**
     * @see Controller#Controller(AccessRole)
     */
    public FriendRequestServlet(AccessRole role) {
        super(role);
        // TODO Auto-generated constructor stub
    }


	@Override
	protected void get(Navigation nav) throws IOException, ServletException {
		
			//ricevo la richiesta
			//creo la notifica per il ricevente
			String askerID = nav.getAttribute("asker");
			String receiverID = nav.getAttribute("receiver");
		
			try{
				
				User receiver = ubr.getUserByID(Integer.parseInt(receiverID));
				User asker = ubr.getUserByID(Integer.parseInt(askerID));
				fsbr.createPendingFriendship(asker, receiver);
				nbr.notifyFriendshipRequest(asker, receiver);
				

			}catch(NoSuchUserException nsue){
				
				//TODO gestirla
				
			}
			
			
		
		
	}

}
