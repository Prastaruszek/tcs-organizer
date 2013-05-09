package models;

import org.junit.Test;
import java.util.Calendar;
import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: stnatic
 * Date: 09.05.13
 * Time: 22:48
 */
public class DisplayStateTest {

    @Test
    public void testSetWeek() throws Exception {
        DisplayState state = new DisplayState();
        state.setWeek(2013, Calendar.MAY, 9);
        Calendar firstDay = state.getFirstDay(),
                 lastDay = state.getLastDay();
        assertEquals(firstDay.get(Calendar.YEAR), 2013);
        assertEquals(firstDay.get(Calendar.MONTH), Calendar.MAY);
        assertEquals(firstDay.get(Calendar.DAY_OF_MONTH), 6);
        assertEquals(lastDay.get(Calendar.YEAR), 2013);
        assertEquals(lastDay.get(Calendar.MONTH), Calendar.MAY);
        assertEquals(lastDay.get(Calendar.DAY_OF_MONTH), 12);

        state.setWeek(2013, Calendar.JANUARY, 1);
        firstDay = state.getFirstDay();
        lastDay = state.getLastDay();
        assertEquals(firstDay.get(Calendar.YEAR), 2012);
        assertEquals(firstDay.get(Calendar.MONTH), Calendar.DECEMBER);
        assertEquals(firstDay.get(Calendar.DAY_OF_MONTH), 31);
        assertEquals(lastDay.get(Calendar.YEAR), 2013);
        assertEquals(lastDay.get(Calendar.MONTH), Calendar.JANUARY);
        assertEquals(lastDay.get(Calendar.DAY_OF_MONTH), 6);
    }

    @Test
    public void testSetNextAndPreviousWeek() throws Exception {
        DisplayState state = new DisplayState();
        state.setWeek(2013, Calendar.JANUARY, 1);
        state.setNextWeek();
        Calendar firstDay = state.getFirstDay(),
                 lastDay = state.getLastDay();
        assertEquals(firstDay.get(Calendar.YEAR), 2013);
        assertEquals(firstDay.get(Calendar.MONTH), Calendar.JANUARY);
        assertEquals(firstDay.get(Calendar.DAY_OF_MONTH), 7);
        assertEquals(lastDay.get(Calendar.YEAR), 2013);
        assertEquals(lastDay.get(Calendar.MONTH), Calendar.JANUARY);
        assertEquals(lastDay.get(Calendar.DAY_OF_MONTH), 13);

        state.setPreviousWeek();
        firstDay = state.getFirstDay();
        lastDay = state.getLastDay();
        assertEquals(firstDay.get(Calendar.YEAR), 2012);
        assertEquals(firstDay.get(Calendar.MONTH), Calendar.DECEMBER);
        assertEquals(firstDay.get(Calendar.DAY_OF_MONTH), 31);
        assertEquals(lastDay.get(Calendar.YEAR), 2013);
        assertEquals(lastDay.get(Calendar.MONTH), Calendar.JANUARY);
        assertEquals(lastDay.get(Calendar.DAY_OF_MONTH), 6);
    }
}
