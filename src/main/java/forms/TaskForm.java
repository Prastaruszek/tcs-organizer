package forms;

import models.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public final class TaskForm extends ModelForm<Task> {
    private EventGroup parent;
    private String title;
    private String comment;
    private int duration;
    private UserProfile profile;
    private List<Resource> resources;
    private EventPriority priority;

    public TaskForm(Task instance, UserProfile profile) {
        this.profile = profile;
        if ( profile == null )
            throw new NullPointerException("Profile cannot be null");
        this.instance = instance;
    }

    @Override
    public boolean isValid() {
        clean();
        if ( title == null ) {
            return false;
        }
        if ( comment == null )
            return false;

        boolean valid = true;

        if ( duration == 0 ){
            this.getErrors().appendError("Time", "Duration can not be 0.");
            valid = false;
        }

        if ( title.isEmpty()) {
            this.getErrors().appendError("Title", "Cannot be empty");
        	valid = false;
        }

        if ( instance != null ) {
            if ( instance.getProfile() != profile )
                return false;
        }

        return valid;
    }

    /**
     * Because we should'nt pass JList to forms, it simply validates the task and returns it so controller can do whatever
     * he wants with it.
     * @return created task
     * @throws ValidationException
     */
    @Override
    public Task save() throws ValidationException {
        boolean isCreate = instance == null;
        if ( instance == null ) {
            setInstance(new Task(
                    parent,
                    comment,
                    duration,
                    profile,
                    priority,
                    resources
            ));
            getInstance().setTitle(title);
        }
        super.save();
        if( !isCreate ){
            instance.setDuration(duration);
            instance.setTitle(title);
            instance.setComment(comment);
            instance.setResources(resources);
            instance.setPriority(priority);
        }
        return getInstance();
    }

    public void setTitle(String title) {
        this.title = title;

    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setParent(EventGroup parent) {
        this.parent = parent;
    }

    public void setResources(List<Resource> resources){
    	this.resources = new LinkedList<>();
    	
    	for(Resource r : resources) {
    		if(r instanceof ResourceFile)
    			this.resources.add(new ResourceFile((ResourceFile)r));
    		else
    			this.resources.add(r);
    	}
    }
    
    public void setPriority(EventPriority priority) {
    	this.priority = priority;
    }
}
