package models;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 04.05.13
 * Time: 22:52
 */
public class EventGroup extends Model implements Serializable {
	
	private static final long serialVersionUID = -8544701639746640340L;
	private String title;

    public EventGroup(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
