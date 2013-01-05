package it.polimi.swimv2.test;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.BeforeClass;

public class BaseTest {
	
	protected static InitialContext ctx;
	
	protected static <T,Q> T lookup(Class<Q> cl) throws NamingException {
		return (T) ctx.lookup("swimv2-ear/"+cl.getSimpleName()+"/remote");
	}
	
	@BeforeClass
	public static void setup() throws NamingException {
		Properties env = new Properties();
		env.setProperty("java.naming.factory.initial","org.jnp.interfaces.NamingContextFactory");
	 	env.setProperty("java.naming.provider.url", "localhost:1099");
	 	env.setProperty("java.naming.factory.url.pkgs", "org.jboss.naming");
	 	ctx = new InitialContext(env);
	 	InitializerTestBeanRemote init = lookup(InitializerTestBean.class);
	 	init.createPredefinedUsers();
	}
	
	@AfterClass
	public static void teardown() throws NamingException {
		InitializerTestBeanRemote init = lookup(InitializerTestBean.class);
		init.deletePredefinedUsers();
	}

}