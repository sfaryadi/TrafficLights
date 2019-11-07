package trafficlights;

public class Event implements Comparable<Event>{
	private String eventType;
	private int time;

	public Event(String eType, int time) {
		eventType = eType;
		this.time = time;
	}

	public String getEventType() {
		return eventType;
	}

	public int getTime() {
		return time;
	}
	
	public int compareTo(Event other) {
		if (time > other.time)
			return 1;
		else if (time < other.time)
			return -1;
		else 
			return 0;
					
	}

}