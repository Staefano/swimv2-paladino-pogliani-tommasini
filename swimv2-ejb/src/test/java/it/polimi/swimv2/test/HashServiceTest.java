package it.polimi.swimv2.test;

import static org.junit.Assert.*;
import it.polimi.swimv2.session.utils.HashService;

import org.junit.Test;

public class HashServiceTest {

	@Test
	public void test() {
		HashService hasher = new HashService();
		String s1 = "The quick brown fox jumps over the lazy dog";
		String s1_sha = "2fd4e1c67a2d28fced849ee1bb76e7391b93eb12";
		assertEquals(s1, s1);
		assertTrue("A", hasher.compareWithHash(s1, hasher.hash(s1)));
		assertEquals("B", s1_sha, hasher.hash(s1));
		assertFalse("D", hasher.compareWithHash(s1, "aaa"));
	}

}
