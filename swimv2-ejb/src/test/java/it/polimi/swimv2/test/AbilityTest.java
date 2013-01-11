package it.polimi.swimv2.test;

import static org.junit.Assert.*;

import java.util.List;

import it.polimi.swimv2.entity.AbilityRequest;
import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.AbilityBean;
import it.polimi.swimv2.session.AuthenticationBean;
import it.polimi.swimv2.session.remote.AbilityBeanRemote;
import it.polimi.swimv2.session.remote.AuthenticationBeanRemote;

import org.junit.Test;

public class AbilityTest extends BaseTest {
	
	@Test
	public void testAbility() throws Exception {

		AuthenticationBeanRemote ab = lookup(AuthenticationBean.class);
		User donald = ab.checkCredentials("donald.duck@example.com",  "paperino");
		User scrooge = ab.checkCredentials("scrooge.mcduck@example.com", "paperondepaperoni");

		AbilityBeanRemote bean = lookup(AbilityBean.class);
		
		int origSize = 0; // bean.retrievePendingRequests().size();
				
		bean.requestAbility("money swimmer", "comment", scrooge);
		bean.requestAbility("matress tester", "comment", donald);
		// two users requesting the same ability, ok, if one accepts the other one accepts too!
		bean.requestAbility("adventurer", "comment", scrooge);
		bean.requestAbility("adventurer", "comment", donald);
		
		List<AbilityRequest> reqs = bean.retrievePendingRequests();
		assertEquals(reqs.size(), origSize + 4);
		AbilityRequest swimmer = null;
		AbilityRequest tester = null;
		AbilityRequest adventurer = null;
		for(AbilityRequest req : reqs) {
            String str = req.getSender().getName() + " " + req.getSender().getSurname() + " : " + req.getAbility() + ", " + req.getTimestamp().toString();
            System.out.println(str);
			if(req.getAbility().equals("money swimmer")) {
				swimmer = req;
			} else if(req.getAbility().equals("matress tester")) {
				tester = req;
			} else if(req.getAbility().equals("adventurer")) {
				adventurer = req;
			}
		}
		// TODO check if really approved ;)
		bean.rejectAbility(adventurer);
		assertEquals(origSize + 1, bean.retrievePendingRequests().size());
		bean.rejectAbility(tester);
	}
}
