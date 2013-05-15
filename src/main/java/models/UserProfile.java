package models;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 05.05.13
 * Time: 00:10
 */
public class UserProfile extends Model {
    private DisplayState state;
    private EventSet events;
    private User user;
    private boolean hasProvidedSchedule;

    public UserProfile() {}

    public UserProfile(DisplayState state, EventSet events, User user) {
        this.state = state;
        this.events = events;
        this.user = user;
    }

    public boolean hasProvidedSchedule() {
        return hasProvidedSchedule;
    }

    public void provideSchedule() {
        this.hasProvidedSchedule = true;
    }

    public DisplayState getState() {
        return state;
    }

    public EventSet getEvents() {
        return events;
    }

    public User getUser() {
        return user;
    }
}
