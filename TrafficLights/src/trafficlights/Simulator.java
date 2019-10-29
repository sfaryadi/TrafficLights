package trafficlights;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Simulator {
	

	public static void main(String[] args) {
		int startTime;
		int endTime;
		int changeFrequency;
		boolean nlGreen;
		boolean elGreen;
		int currentTime;
		int lightChange;
		
		Scanner input = new Scanner(System.in);	
		System.out.println("Please enter the starting time of the simulation");
		startTime = input.nextInt();	
		System.out.println("Please enter the ending time of the simulation");
		endTime = input.nextInt();
		System.out.println("How frequently does the light change?");
		changeFrequency = input.nextInt();

		PriorityQueue<Event> eventQ = new PriorityQueue<>();
		Event current = new Event("NG", startTime);
		lightChange = startTime + changeFrequency;
		eventQ.add(current);
		
				
		while(!eventQ.isEmpty()) {
			Car currentCar;
			currentTime = current.getTime();
			Event test = eventQ.remove();
			switch (test.getEventType()) {
				case "NG":
				
					
					
					elGreen = true;
					nlGreen = false;
					int crossTime = CarGeneration.northQueue.peek().cTime;
					
					if(lightChange > currentTime + crossTime)
					while (nlGreen == true) {
						currentCar = CarGeneration.northQueue.remove();
						Event crossing = new Event("C", currentTime + currentCar.cTime );
						currentTime += currentCar.cTime;
						eventQ.add(crossing);
		
					}
					break;
					
				case "EG":
					elGreen = true;
					nlGreen = false;
					while (elGreen == true) {
						currentCar = CarGeneration.eastQueue.remove();
						Event crossing = new Event("C", currentTime + currentCar.cTime );
						currentTime += currentCar.cTime;
						eventQ.add(crossing);
		
					}
					
					
					break; 
					
				case "C":
					break;
				case "A":
				
					
					break;
		
			}
			
			
			
			
			
			
			
			
			
			
			
		}
			

			
			
			
			
	} 

		
		
}


