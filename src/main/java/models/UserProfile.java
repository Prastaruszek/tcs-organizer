package models;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 05.05.13
 * Time: 00:10
 */
public class UserProfile extends Model {
    private DisplayState state;
    private boolean hasProvidedSchedule;

    public UserProfile() {}

    public UserProfile(DisplayState state) {
        this.state = state;
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
}
