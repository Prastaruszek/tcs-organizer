package forms;

public class ValidationException extends Exception {

	private static final long serialVersionUID = 8885233185024160300L;

	public ValidationException(String message) {
        super(message);
    }
}
