package models;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 04.05.13
 * Time: 22:52
 */
public class EventGroup extends Model {
    private String title;

    public EventGroup(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
