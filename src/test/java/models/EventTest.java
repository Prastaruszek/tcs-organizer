package models;

import java.lang.reflect.Field;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
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
        Event event = new Event("Title", "Comment", new GregorianCalendar(), new GregorianCalendar());
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
}
