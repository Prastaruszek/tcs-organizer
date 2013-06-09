package forms;

import factories.UserProfileFactory;
import models.Event;
import models.EventGroup;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 21.05.13
 * Time: 16:23
 */
public class EventFormTest {
    private EventForm form;
    private Calendar endTime;
    private Calendar startTime;

    @Before
    public void setUp() throws Exception {
        form = new EventForm(UserProfileFactory.create());
        EventGroup group = new EventGroup("Title");
        startTime = new GregorianCalendar();
        startTime.set(Calendar.SECOND, 20);
        endTime = new GregorianCalendar();
        endTime.add(Calendar.HOUR, 2);
        endTime.set(Calendar.SECOND, 30);
        form.setTitle("Title");
        form.setStartTime(startTime);
        form.setEndTime(endTime);
        form.setParent(group);
        form.setComment("");
    }

    @Test
    public void testSaveCleansDateInstances() throws Exception {
        Event e = form.save();
        assertEquals(e.getStartTime().get(Calendar.SECOND), 0);
        assertEquals(e.getEndTime().get(Calendar.SECOND), 0);
    }

    @Test(expected = ValidationException.class)
    public void testSaveThrowsValidationExceptionWhenStartTimeAfterEndTime() throws Exception {
        endTime = new GregorianCalendar();
        endTime.add(Calendar.HOUR, -2);
        form.setEndTime(endTime);
        Event e = form.save();
    }

    @Test
    public void testSaveThrowsValidationExceptionForOverlappingEvents() throws Exception {
        try {
            Event e = form.save();
        } catch ( ValidationException e ) {
            fail("First save should be successful");
        }

        form.setInstance(null);
        Calendar start = new GregorianCalendar();
        start.add(Calendar.HOUR, 1);
        Calendar end = new GregorianCalendar();
        end.add(Calendar.HOUR, 3);
        form.setStartTime(start);
        form.setEndTime(end);
        try {
            Event e = form.save();
            fail("Events are overlapping");
        } catch ( ValidationException e ) {
        }
        start = new GregorianCalendar();
        start.add(Calendar.HOUR, -1);
        end = new GregorianCalendar();
        end.add(Calendar.HOUR, 1);
        form.setStartTime(start);
        form.setEndTime(end);
        try {
            Event e = form.save();
            fail("Events are overlapping");
        } catch ( ValidationException e ) {
        }
    }

    @Test
    public void testModifiesExistingInstance() throws Exception {
        Event e = form.save();
        try {
            e = form.save();
        } catch ( ValidationException ex ) {
            fail("Instance should be updated");
        }
    }
}
