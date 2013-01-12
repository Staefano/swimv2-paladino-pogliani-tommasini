package it.polimi.swimv2.session.exceptions;

public class NoSuchUserException extends OperationFailedException {

	public NoSuchUserException() {
		super();
	}
	
	public NoSuchUserException(Throwable e) {
		super(e);
	}
	
	public NoSuchUserException(String string) {
		super(string);
	}

	private static final long serialVersionUID = -3604004037451647187L;

}
