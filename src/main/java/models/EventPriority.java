package models;

import java.awt.Color;
import java.io.Serializable;

public class EventPriority implements Serializable {
	
	private static final long serialVersionUID = -1840008537717113625L;
	private static final Color[] defaultColor = {Color.RED, Color.PINK, Color.ORANGE, Color.YELLOW, Color.GREEN};
	private int priority;
	
	public EventPriority(int priority) {
		this.priority = priority;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public String getRomanPriority() {
		switch(priority) {
			case 1: return "I";
			case 2: return "II";
			case 3: return "III";
			case 4: return "IV";
			case 5: return "V";
			default: return "";
		}
	}
	
	public Color getDefaultColor(int priority) {
		return defaultColor[priority-1];
	}
	
	public Color getColor(UserProfile profile) {
		Color custom = profile.getPriorityColor(priority);
		if(custom != null)
			return custom;
		return getDefaultColor(priority);
	}
}
