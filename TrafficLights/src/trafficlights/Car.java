package trafficlights;

public class Car {
	int aTime;
	char dir;
	int cTime;
	private static int currentID = 1001;
	int id;
	
	public Car(int a, char d, int c, int i) {
		 aTime = a;
		 dir = d;
		 cTime = c;
		 id = i;
	}
/*	
	public int arrivalTime(int aTime) {
		this.aTime = aTime;
		return aTime;
	}

	public char direction(char dir) {
		this.dir = dir;
		return dir;
	
	}

	public int crossTime(int cTime) {
		this.cTime = cTime;
		return cTime;
	}

	public int vechicleID(int id) {
		this.id = id;
		return id;
	}

	*/

}
