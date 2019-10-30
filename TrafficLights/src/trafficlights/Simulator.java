package trafficlights;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Simulator {
	
	static PriorityQueue<Event> eventQ = new PriorityQueue<>();

	public static void main(String[] args) {
		int startTime;
		int endTime;
		int changeFrequency;
		boolean nlGreen = true;
		boolean elGreen = false;
		int currentTime;
		int lightChange;
		int crossTime;
		
		 LinkedList<Car> northQueue = new LinkedList<>();		
		 LinkedList<Car> southQueue = new LinkedList<>();		
		 LinkedList<Car> eastQueue = new LinkedList<>();		
		 LinkedList<Car> westQueue = new LinkedList<>();	
		 LinkedList<Car> crossingQueue = new LinkedList<>();	

		 
		// Takes input from the user for start time, end time, and light change frequency
		Scanner input = new Scanner(System.in);	
		System.out.println("Please enter the starting time of the simulation");
		startTime = input.nextInt();	
		System.out.println("Please enter the ending time of the simulation");
		endTime = input.nextInt();
		System.out.println("How frequently does the light change?");
		changeFrequency = input.nextInt();
		input.close();
		
		Event current = new Event("A", startTime);
		currentTime = startTime;
		lightChange = currentTime + changeFrequency;
		eventQ.add(current);
		
		
		
		
				
		while(!eventQ.isEmpty()) {
			Car crossingCar;
			Car newArrival;
			currentTime = current.getTime();
			Event test = eventQ.remove();
			switch (test.getEventType()) {
				case "NG":
						
					elGreen = false;
					nlGreen = true;
	
					crossTime = northQueue.peek().cTime;
					
					if(lightChange > currentTime + crossTime) {
						Event crossing = new Event("C", currentTime + crossTime);
						eventQ.add(crossing);
		
					}
					break;
					
				case "EG":
					
					elGreen = true;
					nlGreen = false;
					 crossTime = eastQueue.peek().cTime;
					while (elGreen == true) {
					//	crossingCar = eastQueue.remove();
						Event crossing = new Event("C", currentTime + crossingCar.cTime );
						currentTime += crossingCar.cTime;
						eventQ.add(crossing);
		
					}
					
					
					break; 
					
				case "C":
					if (nlGreen == true && !northQueue.isEmpty()) {
						crossingCar = northQueue.remove();
						System.out.printf("Car %d has crossed the intersection" , crossingCar.id);
						currentTime += crossingCar.cTime;
					}
					else if (elGreen == true && !eastQueue.isEmpty()) {
						crossingCar = eastQueue.remove();
						System.out.printf("Car %d has crossed the intersection" , crossingCar.id);

						currentTime += crossingCar.cTime;
					}
					
					
					
					break;
				case "A":
					elGreen = false;
					nlGreen = true;
					
					newArrival = CarGeneration.carQueue.remove();
					if(newArrival.dir == 'N') {
						northQueue.add(newArrival);
						System.out.printf("Car %d has arrive in the %C queue", newArrival.id,newArrival.dir);
						crossTime = northQueue.peek().cTime;
						
						if(nlGreen == true && lightChange > currentTime + crossTime) {
							Event crossing = new Event("C", currentTime + crossTime);
							crossingQueue.add(newArrival);
							eventQ.add(crossing);
			
						}
					}
					if(newArrival.dir == 'S') {
						southQueue.add(newArrival);
						System.out.printf("Car %d has arrive in the %C queue", newArrival.id,newArrival.dir);

					}if(newArrival.dir == 'E') {
						eastQueue.add(newArrival);
						System.out.printf("Car %d has arrive in the %C queue", newArrival.id,newArrival.dir);

					}if(newArrival.dir == 'W') {
						westQueue.add(newArrival);
						System.out.printf("Car %d has arrive in the %C queue", newArrival.id,newArrival.dir);

					}
				
				
					
					break;
		
			}
			
			
			
			
			
			
			
			
			
			
			
		}
			

			
			
			
			
	} 

		
		
}


