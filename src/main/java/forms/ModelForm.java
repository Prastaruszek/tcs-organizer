package forms;

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
}
