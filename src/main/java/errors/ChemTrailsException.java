package errors;

/**
 * Created by jason on 2016/07/15.
 */
public class ChemTrailsException extends RuntimeException {

	public ChemTrailsException(String message) {
		super(message);
	}

	public ChemTrailsException(String message, Exception e) {
		super(message, e);
	}
}
