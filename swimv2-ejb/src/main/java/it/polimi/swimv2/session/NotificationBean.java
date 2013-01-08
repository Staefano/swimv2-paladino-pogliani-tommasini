package it.polimi.swimv2.session;

import it.polimi.swimv2.entity.Notification;
import it.polimi.swimv2.entity.User;
import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class NotificationBean
 */
@Stateless
@Local(NotificationBeanRemote.class)
public class NotificationBean implements NotificationBeanRemote {

    /**
     * Default constructor. 
     */
    public NotificationBean() {
        // TODO Auto-generated constructor stub
    }

  

	@Override
	public Notification notifyFriendshipRequest(User asker, User receiver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notification notifyFriendshipAccepted(User asker, User receiver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notification notifyAbilityRequest(User asker) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notification notifyAbilityAccepted(User asker) {
		// TODO Auto-generated method stub
		return null;
	}  }


