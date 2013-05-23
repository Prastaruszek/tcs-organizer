package models;

import factories.EventFactory;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;


/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 04.05.13
 * Time: 23:10
 */
public class EventTest {
    @org.junit.Test
    public void testGetTitle() throws Exception {
        Event event = EventFactory.create();
        EventGroup group = mock(EventGroup.class);
        Field parent = event.getClass().getDeclaredField("parent");
        parent.setAccessible(true);
        parent.set(event, group);
        String mockedTitle = "MockTest";
        when(group.getTitle()).thenReturn(mockedTitle);
        String title = event.getTitle();
        verify(group).getTitle();
        assertEquals(title, mockedTitle);
    }

    @org.junit.Test
    public void testDuration() throws Exception {
        GregorianCalendar now = new GregorianCalendar();
        GregorianCalendar tomorrow = (GregorianCalendar) now.clone();
        tomorrow.add(GregorianCalendar.DAY_OF_MONTH, 1);
        Event event = EventFactory.create();
        event.setStartTime(now);
        event.setEndTime(tomorrow);
        assertEquals(24*60*60, event.duration());
    }

    @org.junit.Test
    public void testIsBetween() throws Exception {
        Calendar before, after, now;
        now = new GregorianCalendar();
        after = (Calendar) now.clone();
        before = (Calendar) after.clone();
        before.add(Calendar.DAY_OF_MONTH, 6);
        Event event = EventFactory.create();
        event.setStartTime(after);
        event.setEndTime(before);
        assertTrue(event.isBetween(after, before));

        after = (Calendar) after.clone();
        after.add(Calendar.DAY_OF_MONTH, 1);
        assertFalse(event.isBetween(after, before));

        after = (Calendar) now.clone();
        before = (Calendar) before.clone();
        before.add(Calendar.DAY_OF_MONTH, -2);
        assertFalse(event.isBetween(after, before));

        after = (Calendar) now.clone();
        after.add(Calendar.DAY_OF_MONTH, -2);
        before = (Calendar) before.clone();
        before.add(Calendar.DAY_OF_MONTH, 2);
        assertTrue(event.isBetween(after, before));

        event = EventFactory.create();
        event.setStartTime(null);
        assertFalse(event.isBetween(after, before));

        event = EventFactory.create();
        event.setEndTime(null);
        assertFalse(event.isBetween(after, before));

        event = EventFactory.create();
        assertTrue(event.isBetween(event.getStartTime(), event.getEndTime()));
    }
}
