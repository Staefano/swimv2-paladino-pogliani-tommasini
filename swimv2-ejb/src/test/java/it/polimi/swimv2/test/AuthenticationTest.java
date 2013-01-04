package it.polimi.swimv2.test;

import static org.junit.Assert.*;

import it.polimi.swimv2.session.AuthenticationBean;
import it.polimi.swimv2.session.AuthenticationBeanRemote;
import it.polimi.swimv2.session.exceptions.NoSuchUserException;

import org.junit.Test;

import it.polimi.swimv2.entity.User;

public class AuthenticationTest extends BaseTest {
	
	@Test
	public void testPasswordChecking() throws Exception {
		AuthenticationBeanRemote ab = lookup(AuthenticationBean.class);
		User donald = ab
				.checkCredentials("donald.duck@example.com", "paperino");
		assertNotNull(donald);
		assertEquals(donald.getName(), "Donald");
		try {
			assertNull(ab.checkCredentials("mickey.mouse@example.com", "ciao"));
			fail();
		} catch (NoSuchUserException e) {
			// OK!
		}
		try {
			assertNotNull(ab.checkCredentials("aaa", "mickey"));
		} catch (NoSuchUserException e) {
			// OK!
		}
	}

}
