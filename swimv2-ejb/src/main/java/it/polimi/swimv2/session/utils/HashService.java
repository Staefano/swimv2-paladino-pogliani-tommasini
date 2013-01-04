package it.polimi.swimv2.session.utils;

import it.polimi.swimv2.session.exceptions.ServerErrorException;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

/**
 * Performs operations related to password hashing and salting
 */
public class HashService {
	
	private static final String ALGORITHM = "SHA-1";
	private static final String ENCODING = "UTF-8";
	
	public String hash(String token) {
		try {
			MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
			digest.update(token.getBytes(ENCODING));
			return Hex.encodeHexString(digest.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new ServerErrorException(
					"Error hashing password - no such algorithm");
		} catch (UnsupportedEncodingException e) {
			throw new ServerErrorException(
					"Error hashing password - no such encoding");
		}
	}

	public boolean compareWithHash(String unhashed, String hashed) {
		return hash(unhashed).equals(hashed);
	}
	
}
