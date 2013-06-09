package forms;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * ModelForm is abstract superclass which is used
 * to validate model data, provide information
 * about error messages and handle process
 * of creating new Model objects and updating existing ones.
 *
 * All changes take place when save method is called.
 *
 * <p>
 * <b>Instance</b> is object, which is currently edited. It might have null
 * value which indicates, that we want to create a new object.
 * </p>
 *
 * @author      stnatic
 */
public abstract class ModelForm<T> {
    protected T instance;
    private ErrorList errors;

    public T getInstance() {
        return instance;
    }

    /**
     * Sets form instance.
     * @param instance new form instance
     * @see ModelForm
     */
    public void setInstance(T instance) {
        this.instance = instance;
    }

    /**
     * <p>
     * Checks whether form is valid.
     * @return <code>true</code> if the model data is valid
     * and <code>false</code> otherwise. Good practice is to
     * create error messages using syntax:<br />
     * <code>this.getErrors().appendError("GroupName", "Given field was not valid.");</code>
     * </p>
     *
     * <p>Error messages can be displayed later using #getErrorsDisplay().</p>
     *
     * @see #getErrorsDisplay()
     */
    public abstract boolean isValid();

    /**
     * <p>
     * Updates/creates new instance of object. Calls #isValid() in
     * order to ensure saved data validity.
     * </p>
     *
     * <p>Error messages can be displayed later using #getErrorsDisplay().</p>
     *
     * @throws ValidationException if form was not valid
     * @return created/updated object.
     * @see #isValid()
     */
    public T save() throws ValidationException {
        if ( !isValid() ) {
            throw new ValidationException("Form data was not valid");
        }
        return null;
    }

    /**
     * Method that should be called before data validation
     * takes place. It automatically invokes all methods,
     * which names start with "clean". Can be used to preprocess
     * data entered by user, e.g. we might want to implement method
     * cleanStartTime, which would parse string entered by user,
     * e.g. "2013-06-01" and store it for object constructor invocation.
     *
     * @see #isValid()
     */
    public void clean() {
        Method[] methods = this.getClass().getDeclaredMethods();
        for ( Method method : methods ) {
            if ( method.getName().startsWith("clean") && !method.getName().equals("clean") ) {
                method.setAccessible(true);
                try {
                    method.invoke(this);
                } catch (IllegalAccessException | InvocationTargetException ignored) {
                }
            }
        }
        if( errors != null )
            errors = null;
    }

    /**
     * Method that should be called before data validation
     * takes place. It automatically invokes all methods,
     * which names start with "clean". Can be used to preprocess
     * data entered by user, e.g. we might want to implement method
     * cleanStartTime, which would parse string entered by user,
     * e.g. "2013-06-01" and store it for object constructor invocation.
     *
     * @see ErrorList
     */
    public ErrorList getErrors() {
        if ( errors == null )
            errors = new ErrorList();
        return errors;
    }

    /**
     * Method for displaying form error messages as list
     *
     * @return <code>String</code> with HTML containing
     * form validation errors displayed as list
     *
     * @see ErrorList
     */
    public String getErrorsDisplay() {
        return getErrors().getErrorsDisplay();
    }

    /**
     * <p>
     * Class for storing / processing form error messages.
     * </p>
     *
     * @author      stnatic
     * @see #appendError(String, String)
     * @see #getErrorsDisplay()
     */
    class ErrorList {
        protected  Map<String, List<String>> errors;

        ErrorList() {
            this.errors = new HashMap<>();
        }

        /**
         *
         * @param field <code>String</code> containing, name of
         *              list item, under which message is displayed
         * @param message <code>String</code> containing message
         *                to be displayed
         */
        public void appendError(String field, String message) {
            List<String> list = errors.get(field);
            if ( list == null )
                list = new LinkedList<>();
            list.add(message);
            errors.put(field, list);
        }

        /**
         * Returns HTML list representation of form errors.
         * @return <code>String</code> containing HTML list with
         * error messages
         */
        public String getErrorsDisplay() {
            StringBuilder sb = new StringBuilder("<html>");
            sb.append("<h3>Input was not valid</h3>");

            for ( Map.Entry<String, List<String>> e : errors.entrySet() ) {

                sb.append("<h4>");
                sb.append(e.getKey()).append(":");
                sb.append("</h4>");
                for ( String s : e.getValue() ) {
                    sb.append("<ul>");
                    sb.append("<li>");
                    sb.append(s);
                    sb.append("</li>");
                    sb.append("</ul>");
                }
            }
            sb.append("</html>");
            System.out.println(sb.toString());
            return sb.toString();
        }
    }
}
