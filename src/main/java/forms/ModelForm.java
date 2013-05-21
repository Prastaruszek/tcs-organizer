package forms;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public abstract class ModelForm<T> {
    protected T instance;

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
}
