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
    private Event event;

    public TaskForm(Task instance, Event event) {
        if ( profile == null )
            throw new NullPointerException("Profile cannot be null");
        this.instance = instance;
        this.event = event;
    }

    @Override
    public boolean isValid() {
        clean();

        if ( parent == null )
            return false;
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
            if ( instance.getParent() != parent )
                return false;
        }

        return valid;
    }

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
        }
        super.save();
        if ( isCreate ) {
            event.addTask(getInstance());
        } else {
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
