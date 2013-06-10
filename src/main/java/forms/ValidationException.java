package forms;

/**
 * Exception which is thrown in case of attempt to save
 * invalid <code>ModelForm</code>.
 * @author stnatic
 * @see ModelForm
 */
public class ValidationException extends Exception {

	private static final long serialVersionUID = 8885233185024160300L;

	public ValidationException(String message) {
        super(message);
    }
}
