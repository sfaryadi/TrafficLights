package trafficlights;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class CarGeneration  {

	public static void main(String[] args) throws Exception {
		
		
		
		LinkedList<Car> northQueue = new LinkedList<>();		
		LinkedList<Car> southQueue = new LinkedList<>();		
		LinkedList<Car> eastQueue = new LinkedList<>();		
		LinkedList<Car> westQueue = new LinkedList<>();		

		File file = new File("Samplecar");
		Scanner scan = new Scanner(file);
	
		for (int i = 0; i < 10; i++) {
			Car newCar = new Car(scan.nextInt(), scan.next().charAt(0),scan.nextInt(),scan.nextInt());
			System.out.printf("Arrival Time = %d Direction = %c Cross Time %d ID = %d\n", newCar.aTime, newCar.dir, newCar.cTime, newCar.id);
			if(newCar.dir == 'N') {
				northQueue.add(newCar);
			}
			if(newCar.dir == 'S') {
				southQueue.add(newCar);
			}if(newCar.dir == 'E') {
				eastQueue.add(newCar);
			}if(newCar.dir == 'W') {
				westQueue.add(newCar);
			}
			
		}
		
	//	System.out.println(northQueue.size());
	//	System.out.print(northQueue.getFirst().id);
		
	
		

		scan.close();
	}
	

}
