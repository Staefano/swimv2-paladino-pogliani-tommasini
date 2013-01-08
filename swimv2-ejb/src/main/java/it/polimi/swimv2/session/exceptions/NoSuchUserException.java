package it.polimi.swimv2.session.exceptions;

public class NoSuchUserException extends OperationFailedException {

	public NoSuchUserException() {
		super();
	}
	
	public NoSuchUserException(String string) {
		super(string);
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = -3604004037451647187L;

}
