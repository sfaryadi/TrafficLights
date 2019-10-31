package trafficlights;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class CarGeneration  {

	static LinkedList<Car> carQueue = new LinkedList<>();		

	static LinkedList<Car> northQueue = new LinkedList<>();		
	static LinkedList<Car> southQueue = new LinkedList<>();		
	static LinkedList<Car> eastQueue = new LinkedList<>();		
	static LinkedList<Car> westQueue = new LinkedList<>();	

	public static void main(String[] args) throws Exception {
		

		File file = new File("Samplecar");
		Scanner scan = new Scanner(file);
	
		for (int i = 0; i < 10; i++) {
			Car newCar = new Car(scan.nextInt(), scan.next().charAt(0),scan.nextInt(),scan.nextInt());
			//System.out.printf("Arrival Time = %d Direction = %c Cross Time %d ID = %d\n", newCar.aTime, newCar.dir, newCar.cTime, newCar.id);
			Event arrival = new Event("A", newCar.aTime);
			Simulator.eventQ.add(arrival);
			//carQueue.add(newCar);
			if(newCar.dir == 'N') {
				northQueue.add(newCar);
				System.out.printf("Car %d has arrived in the %C queue at %d\n", newCar.id,newCar.dir, newCar.aTime);

			}
			if(newCar.dir == 'S') {
				southQueue.add(newCar);
				System.out.printf("Car %d has arrived in the %C queue at %d\n", newCar.id,newCar.dir, newCar.aTime);

			}if(newCar.dir == 'E') {
				eastQueue.add(newCar);
				System.out.printf("Car %d has arrived in the %C queue at %d\n", newCar.id,newCar.dir, newCar.aTime);

			}if(newCar.dir == 'W') {
				westQueue.add(newCar);
				System.out.printf("Car %d has arrived in the %C queue at %d\n", newCar.id,newCar.dir, newCar.aTime);

			}
			
		}
	
	
		

		scan.close();
	}
	

}
