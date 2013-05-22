package forms;

import com.sun.org.apache.bcel.internal.classfile.StackMapEntry;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class ModelForm<T> {
    protected T instance;
    private ErrorList errors;

    public T getInstance() {
        return instance;
    }

    public void setInstance(T instance) {
        this.instance = instance;
    }

    public abstract boolean isValid();

    public T save() throws ValidationException {
        if ( !isValid() ) {
            throw new ValidationException("Form data was not valid");
        }
        return null;
    }

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
    }

    public ErrorList getErrors() {
        if ( errors == null )
            errors = new ErrorList();
        return errors;
    }

    public String getErrorsDisplay() {
        return getErrors().getErrorsDisplay();
    }

    class ErrorList {
        protected  Map<String, String> errors;

        ErrorList() {
            this.errors = new HashMap<>();
        }

        public void appendError(String field, String message) {
            errors.put(field, message);
        }

        public String getErrorsDisplay() {
            StringBuilder sb = new StringBuilder("<html>");
            sb.append("<h3>Input was not valid</h3>");
            sb.append("<ul>");
            for ( Map.Entry<String,String> e : errors.entrySet() ) {
                sb.append("<li style=\"margin-left: -10px\">");
                sb.append(e.getValue());
                sb.append("</li>");
            }
            sb.append("</ul>");
            sb.append("</html>");
            return sb.toString();
        }
    }
}
