package models;

import java.awt.Color;
import java.io.Serializable;

public enum EventPriority implements Serializable {
	
	ONE(1, "I"), TWO(2, "II"), THREE(3, "III"), FOUR(4, "IV"), FIVE(5, "V");
	
	private static final long serialVersionUID = -1840008537717113625L;
	private static final Color[] defaultColor = {Color.RED, Color.PINK, Color.ORANGE, Color.YELLOW, Color.GREEN};
	private int priority;
	private String roman;
	
	private EventPriority(int priority, String roman) {
		this.priority = priority;
		this.roman = roman;
	}
	
	public static EventPriority getEnum(int priority) {
		switch(priority) {
			case 1: return ONE;
			case 2: return TWO;
			case 3: return THREE;
			case 4: return FOUR;
			default: return FIVE;
		}
	}
	
	public int getPriority() {
		return priority;
	}
	
	public String getRomanPriority() {
		return roman;
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
	
	@Override
	public String toString() {
		return roman;
	}
}
