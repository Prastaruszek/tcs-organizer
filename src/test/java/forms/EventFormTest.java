package forms;

import models.Event;
import models.EventGroup;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 21.05.13
 * Time: 16:23
 */
public class EventFormTest {
    @Test
    public void testSave() throws Exception {
        EventForm form = new EventForm();
        EventGroup group = new EventGroup("Title");
        Calendar startTime = new GregorianCalendar();
        startTime.set(Calendar.SECOND, 20);
        Calendar endTime = new GregorianCalendar();
        endTime.add(Calendar.HOUR, 2);
        endTime.set(Calendar.SECOND, 30);
        form.setStartTime(startTime);
        form.setEndTime(endTime);
        form.setParent(group);
        form.setComment("");
        Event e = form.save();
        assertEquals(e.getStartTime().get(Calendar.SECOND), 0);
        assertEquals(e.getEndTime().get(Calendar.SECOND), 0);
    }
}
