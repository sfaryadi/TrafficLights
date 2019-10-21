package trafficlights;

import java.util.Scanner;

public class Simulator {

	public static void main(String[] args) {
		int startTime;
		int endTime;
		int changeFrequency;
		Scanner input = new Scanner(System.in);

		System.out.println("Please enter the starting time of the simulation");
		startTime = input.nextInt();
		
		System.out.println("Please enter the ending time of the simulation");
		endTime = input.nextInt();

		System.out.println("How frequently does the light change?");
		changeFrequency = input.nextInt();
		
		
		
	}

}
