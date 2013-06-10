package controllers;

import com.toedter.calendar.JCalendar;
import views.gui.DatePicker;

import java.awt.event.ActionEvent;
import java.util.GregorianCalendar;

/**
 * Handles date picking by a user.
 */
public abstract class DatePickerController extends Controller {
    protected JCalendar jCalendar;
    protected DatePicker picker;
    public DatePickerController(){
    	
    }
    public DatePickerController(JCalendar jCalendar, DatePicker picker) {
        this.jCalendar = jCalendar;
        this.picker = picker;
    }

    /**
     * Sets all fields of the class.
     * @param jCalendar calendar.
     * @param picker DatePicker view.
     */
    public void setUp(JCalendar jCalendar, DatePicker picker){
    	this.jCalendar = jCalendar;
        this.picker = picker;
    }

    /**
     * Returns picked calendar.
     * @return GregorianCalendar picked calendar.
     */
    public GregorianCalendar getGregorianCalendar(){
    	GregorianCalendar calendar = new GregorianCalendar();
    	calendar.setTime(jCalendar.getDate());
    	return calendar;
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
    	picker.dispose();
    }
}
