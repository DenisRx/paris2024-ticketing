package exception;

public class CompetitionNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CompetitionNotFoundException(long id) {
		super(String.format("Could not find competition %s", id));
	}

}
