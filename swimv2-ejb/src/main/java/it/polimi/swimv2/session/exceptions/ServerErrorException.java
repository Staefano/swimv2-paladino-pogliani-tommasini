package it.polimi.swimv2.session.exceptions;

/**
 * A generic exception for runtime server errors thrown in session bean classes.
 * This is used to handle error that don't depend upon parameters coming from
 * the user, but only upon error conditions internal to server classes that
 * normally should not happen.
 */
public class ServerErrorException extends RuntimeException {

	private static final long serialVersionUID = 264617505774126474L;
	
	public ServerErrorException(String message, Throwable cause) {
		super(message, cause);
	}
	
	

}
