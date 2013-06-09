package models;

import java.awt.Color;
import java.io.Serializable;

/** This class contains constant enum values representing event priorities.
 * There are five event priorities numbered from 1 (most important) to 5 (least important).
 * Each priority defines a color which is used for displaying event in graphical user interface.
 */
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
	
	/** Returns proper object representing given priority.
	 * @param priority priority number, must be in [1,5].
	 * @return object representing given priority.
	 */
	public static EventPriority getEnum(int priority) {
		switch(priority) {
			case 1: return ONE;
			case 2: return TWO;
			case 3: return THREE;
			case 4: return FOUR;
			default: return FIVE;
		}
	}
	
	/** Returns priority value of this event priority.
     * @return priority value of this event priority.
     */
	public int getPriority() {
		return priority;
	}
	
	/** Returns priority value of this event priority in Roman notation.
     * @return priority value of this event priority in Roman notation.
     */
	public String getRomanPriority() {
		return roman;
	}
	
	/** Returns default color of this event priority.
     * @return priority value of this event priority.
     */
	public Color getDefaultColor(int priority) {
		return defaultColor[priority-1];
	}
	
	/** Returns color of this event priority for a given user profile.
	 * If a custom color for the user is defined, it will be used. 
	 * Otherwise the default color will be returned.
	 * @param profile the user profile
     * @return color of this event priority for the given user profile.
     */
	public Color getColor(UserProfile profile) {
		Color custom = profile.getPriorityColor(priority);
		if(custom != null)
			return custom;
		return getDefaultColor(priority);
	}
	
	/** Returns priority value of this event priority in Roman notation.
     * @return priority value of this event priority in Roman notation.
     */
	@Override
	public String toString() {
		return roman;
	}
}
