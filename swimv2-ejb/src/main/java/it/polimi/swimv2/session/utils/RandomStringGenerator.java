package it.polimi.swimv2.session.utils;

import java.util.Random;

/**
 * Generates random fixed-length alphanumeric strings
 * 
 * This class is "heavily inspired" by erickson's reply on this SO question:
 * <pre>http://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string-in-java</pre>
 */
public class RandomStringGenerator {

	private static final int NUMBER_OF_SYMBOLS = 36;
	private final char[] symbols = new char[NUMBER_OF_SYMBOLS];
	private final Random random = new Random();
	private final char[] buf;

	public RandomStringGenerator(int length) {
		if (length < 1) {
			throw new IllegalArgumentException("length < 1: " + length);
		}
		initSymbols();
		buf = new char[length];
	}

	/**
	 * Generates a new fixed-length, random alphanumeric string.
	 */
	public String nextString() {
		for (int idx = 0; idx < buf.length; ++idx) {
			buf[idx] = symbols[random.nextInt(symbols.length)];
		}
		return new String(buf);
	}

	private void initSymbols() {
		final int digits = 10;
		for (int idx = 0; idx < digits; ++idx) {
			symbols[idx] = (char) ('0' + idx);
		}
		for (int idx = digits; idx < NUMBER_OF_SYMBOLS; ++idx) {
			symbols[idx] = (char) ('a' + idx - digits);
		}
	}
}