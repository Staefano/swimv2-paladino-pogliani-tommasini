package it.polimi.swimv2.session.exceptions;

public class OperationFailedException extends Exception {

	private static final long serialVersionUID = -4990171899368834447L;
	
	public OperationFailedException(String string) {
		super(string);
	}
	
	public OperationFailedException() {
		super();
	}

}
