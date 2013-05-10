package views.gui.components;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import models.Event;

public class JEventDisplay extends JComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6850617078923766896L;
	private int rowCount=14;
	private int startingHour = 8;
	String[] daysOfTheWeek = {"Monday", "Tuesday", "Œroda(via pic)", "Thursday", "Friday", "Saturday", "Sunday"};
	Iterable<Event> events= null;
	public Iterable<Event> getEvents() {
		return events;
	}
	public void setEvents(Iterable<Event> events) {
		this.events = events;
	}
	@Override
	protected void paintComponent(Graphics g) {
		int colWidth = getWidth()/8;
		int rowHeight = getHeight()/rowCount;
		drawRowsAndColumns(colWidth, rowHeight, g);
	}
	
	private void drawRowsAndColumns(int colWidth,int rowHeight,Graphics g){
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0,0,getWidth(),rowHeight);
		g.fillRect(0,0,colWidth,getHeight());
		g.setColor(Color.WHITE);
		g.fillRect(colWidth,rowHeight,getWidth(),getHeight());
		g.setColor(Color.BLACK);
		for(int i=1;i<=rowCount;i++){
			int hour = startingHour+i-1;
			String hourStr = (hour>=10?""+hour:"0"+hour)+":00";
			g.drawString(hourStr, 
					(int) (colWidth/2-g.getFontMetrics().getStringBounds(hourStr, g).getWidth()/2),
					rowHeight*(i+1)-g.getFontMetrics().getHeight()/2);
			g.drawLine(0,rowHeight*i,getWidth(),rowHeight*i);
		}
		for(int i=1;i<=7;i++){
			g.drawString(daysOfTheWeek[i-1],
					colWidth*i+(int) (colWidth/2 - g.getFontMetrics().getStringBounds(daysOfTheWeek[i-1],g).getWidth()/2),
					rowHeight-g.getFontMetrics().getHeight()/2);
			g.drawLine(colWidth*i,0,colWidth*i,getHeight());		
		}
		drawEvents(g);
		// TODO: tooooooltips or something to show details about event like on pic.
	}
	
	private void drawEvents(Graphics g){
		// TODO: draw those events
	}
}
