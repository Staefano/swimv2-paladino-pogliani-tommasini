package it.polimi.swimv2.test;

import static org.junit.Assert.assertEquals;
import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.AuthenticationBeanRemote;
import it.polimi.swimv2.session.MessageManagerBeanRemote;

import org.junit.Test;

public class MessageManagerTest extends BaseTest {
	
	@Test
	public void testMessages() throws Exception {
		AuthenticationBeanRemote ab = lookup("AuthenticationBean");
		User donald = ab.checkCredentials("donald.duck@example.com",  "paperino");
		User scrooge = ab.checkCredentials("scrooge.mcduck@example.com", "paperondepaperoni");
		MessageManagerBeanRemote mmb = lookup("MessageManagerBean");
		final String DTS = "For this month, I don't have the money I owe to you!";
		final String STD = "Remember that the house you live in is mine!";
		int size0 = mmb.getByUsername(donald, scrooge).size();
		int size1 = mmb.getByUsername(scrooge, donald).size();
		mmb.write(donald, scrooge, DTS);
		mmb.write(scrooge, donald, STD);
		assertEquals(mmb.getByUsername(donald, scrooge).size(), size0 + 1);
		assertEquals(mmb.getByUsername(scrooge, donald).get(size1).getText(), STD);
		assertEquals(mmb.getByUsername(donald, scrooge).get(size0).getText(), DTS);
	}
	
}
