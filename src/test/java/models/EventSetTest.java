package models;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class EventSetTest {
    private EventSet eventSet;
    private Event[] between;
    private Event[] overlapping;
    private Calendar start;
    private Calendar end;
    private Event[] allEvents;

    @Before
    public void setUp() throws Exception {
        start = new GregorianCalendar();
        end = new GregorianCalendar();
        eventSet  = new EventSet();
        between = new Event[] {
            mock(Event.class),
            mock(Event.class),
        };
        for ( Event e : between ) {
            when(e.isBetween(any(Calendar.class), any(Calendar.class))).thenReturn(Boolean.TRUE);
            eventSet.add(e);
        }
        overlapping = new Event[] {
                mock(Event.class),
                mock(Event.class),
        };
        for ( Event e : overlapping ) {
            when(e.overlaps(any(Calendar.class), any(Calendar.class))).thenReturn(Boolean.TRUE);
            eventSet.add(e);
        }
        allEvents = new Event[0];
        allEvents = eventSet.toArray(allEvents);
    }

    @SuppressWarnings("deprecation")
	@Test
    public void testBetween() throws Exception {
        EventSet result = eventSet.between(start, end);
        assertEquals(Arrays.asList(between).size(), result.size());
        assertTrue(result.containsAll(Arrays.asList(between)));
        assertTrue(eventSet.containsAll(Arrays.asList(allEvents)));

        for ( Event e: allEvents ) {
            verify(e, times(1)).isBetween(any(Calendar.class), any(Calendar.class));
        }
    }

    @SuppressWarnings("deprecation")
	@Test
    public void testOverlapping() throws Exception {
        EventSet result = eventSet.overlapping(start, end);
        assertEquals(Arrays.asList(overlapping).size(), result.size());
        assertTrue(result.containsAll(Arrays.asList(overlapping)));
        assertTrue(eventSet.containsAll(Arrays.asList(allEvents)));

        for ( Event e: allEvents ) {
            verify(e, times(1)).overlaps(any(Calendar.class), any(Calendar.class));
        }
    }
}
