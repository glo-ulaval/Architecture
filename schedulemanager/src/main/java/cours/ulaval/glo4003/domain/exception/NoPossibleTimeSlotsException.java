package cours.ulaval.glo4003.domain.exception;

public class NoPossibleTimeSlotsException extends Exception {
	private static final long serialVersionUID = 1L;

	public NoPossibleTimeSlotsException(String message) {
		super(message);
	}

}
