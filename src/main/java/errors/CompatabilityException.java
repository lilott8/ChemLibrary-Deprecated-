package errors;

/**
 * Created by jason on 2016/07/15.
 */
public class CompatabilityException extends ChemTrailsException {

	public CompatabilityException(String message) {
		super(message);
	}

	public CompatabilityException(String message, Exception e) {
		super(message, e);
	}

}
