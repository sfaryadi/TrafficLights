package trafficlights;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Simulator {

	static PriorityQueue<Event> eventQ = new PriorityQueue<>();

	public static void main(String[] args) throws Exception {

		CarGeneration.main(null);
//creates variables for the start time, end time, and light change frequency. and the number of cars in each direction		
		int startTime;
		int endTime;
		int changeFrequency;
		int northCars = 0;
		int southCars = 0;
		int westCars = 0;
		int eastCars = 0;

		int currentTime;
		int lightChange;

// Takes input from the user for start time, end time, and light change frequency
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the starting time of the simulation");
		startTime = input.nextInt();
		System.out.println("Please enter the ending time of the simulation");
		endTime = input.nextInt();
		System.out.println("How frequently does the light change?");
		changeFrequency = input.nextInt();
		input.close();

		currentTime = startTime;
		lightChange = currentTime + changeFrequency;

// variables which determine whether a car is in the intersection, if 0 yes, else than a car is crossing
		int northCrossing = 0;
		int southCrossing = 0;
		int eastCrossing = 0;
		int westCrossing = 0;
// variables for the total wait and average wait times in each directions		
		int tempWait = 0;
		int northWait = 0;
		int northAvg;
		int southWait = 0;
		int southAvg;
		int eastWait = 0;
		int eastAvg;
		int westWait = 0;
		int westAvg;

//variables for the longest wait times in each direction
		int northLongestWaitTime = 0;
		int northLongestWaitID = 0;
		int eastLongestWaitTime = 0;
		int eastLongestWaitID = 0;
		int southLongestWaitTime = 0;
		int southLongestWaitID = 0;
		int westLongestWaitTime = 0;
		int westLongestWaitID = 0;

//initalizes lights and prints which one starts as green
		boolean nlGreen = true;
		boolean elGreen = false;
		if (nlGreen == true) {
			System.out.println("The North/South light is green.");
		} else {
			System.out.println("The East/West light is green.");

		}
// does everything while the endtime hasn't been reach and there are still cars in the queues
		while ((!CarGeneration.northQueue.isEmpty() || !CarGeneration.southQueue.isEmpty()
				|| !CarGeneration.westQueue.isEmpty() || !CarGeneration.eastQueue.isEmpty())
				&& currentTime <= endTime) {
			Car crossingCar;
			Car newArrival;

// goes through carQueue and handles arrival events
			if (!CarGeneration.carQueue.isEmpty() && CarGeneration.carQueue.peek().aTime <= currentTime) {
				newArrival = CarGeneration.carQueue.remove();
				if (newArrival.dir == 'N' && newArrival.aTime >= startTime) {
					System.out.printf("Car %d has arrived in the %c queue at %d\n", newArrival.id, newArrival.dir,
							newArrival.aTime);
					northCars++;
				} else if (newArrival.dir == 'N' && newArrival.aTime < startTime) {
					CarGeneration.northQueue.remove();
					CarGeneration.carQueue.remove();
				}
				if (newArrival.dir == 'S' && newArrival.aTime >= startTime) {
					System.out.printf("Car %d has arrived in the %c queue at %d\n", newArrival.id, newArrival.dir,
							newArrival.aTime);
					southCars++;

				} else if (newArrival.dir == 'S' && newArrival.aTime < startTime) {
					CarGeneration.southQueue.remove();
					CarGeneration.carQueue.remove();
				}
				if (newArrival.dir == 'E' && newArrival.aTime >= startTime) {
					System.out.printf("Car %d has arrived in the %c queue at %d\n", newArrival.id, newArrival.dir,
							newArrival.aTime);
					eastCars++;

				} else if (newArrival.dir == 'E' && newArrival.aTime < startTime) {
					CarGeneration.eastQueue.remove();
					CarGeneration.carQueue.remove();
				}
				if (newArrival.dir == 'W' && newArrival.aTime >= startTime) {
					System.out.printf("Car %d has arrived in the %c queue at %d\n", newArrival.id, newArrival.dir,
							newArrival.aTime);
					westCars++;

				} else if (newArrival.dir == 'W' && newArrival.aTime < startTime) {
					CarGeneration.westQueue.remove();
					CarGeneration.carQueue.remove();
				}

			}
//when it's time for a light change, switchces boolean, increments when lightChange is due, and prints trace statement
			if (lightChange == currentTime) {
				if (nlGreen == true) {
					nlGreen = false;
					elGreen = true;
					lightChange += changeFrequency;
					System.out.printf("The light has changed at %d, the East/West light is now green\n", currentTime);
				} else {
					nlGreen = true;
					elGreen = false;
					lightChange += changeFrequency;
					System.out.printf("The light has changed at %d, the North/South light is now green\n", currentTime);

				}

			}
// checks which light is green and if no car is crossing in that direction it crosses the intersection
			if (nlGreen == true && northCrossing == 0) {
				if (!CarGeneration.northQueue.isEmpty() && CarGeneration.northQueue.peek().aTime <= currentTime) {
					crossingCar = CarGeneration.northQueue.peek();
					northCrossing = CarGeneration.northQueue.peek().cTime;
					System.out.printf("Car %d has entered the intersection heading %c at %d\n", crossingCar.id,
							crossingCar.dir, currentTime);

				}
			} else if (northCrossing != 0) {
				northCrossing--;

				if (northCrossing == 0) {
					crossingCar = CarGeneration.northQueue.peek();

					System.out.printf("Car %d has crossed the intersection heading %c at %d\n", crossingCar.id,
							crossingCar.dir, currentTime);
					tempWait = currentTime - crossingCar.aTime;
					northWait += tempWait;
					if (tempWait > northLongestWaitTime) {
						northLongestWaitTime = tempWait;
						northLongestWaitID = crossingCar.id;
					}
					CarGeneration.northQueue.remove();

				}
			}

			if (nlGreen == true && southCrossing == 0) {

				if (!CarGeneration.southQueue.isEmpty() && CarGeneration.southQueue.peek().aTime <= currentTime) {
					crossingCar = CarGeneration.southQueue.peek();
					southCrossing = CarGeneration.southQueue.peek().cTime;

					System.out.printf("Car %d has entered the intersection heading %c at %d\n", crossingCar.id,
							crossingCar.dir, currentTime);

				}
			} else if (southCrossing != 0) {
				southCrossing--;
				if (southCrossing == 0) {
					crossingCar = CarGeneration.southQueue.peek();

					System.out.printf("Car %d has crossed the intersection heading %c at %d\n", crossingCar.id,
							crossingCar.dir, currentTime);
					tempWait = currentTime - crossingCar.aTime;
					southWait += tempWait;
					if (tempWait > southLongestWaitTime) {
						southLongestWaitTime = tempWait;
						southLongestWaitID = crossingCar.id;
					}
					CarGeneration.southQueue.remove();

				}
			}

			if (elGreen == true && eastCrossing == 0) {

				if (!CarGeneration.eastQueue.isEmpty() && CarGeneration.eastQueue.peek().aTime <= currentTime) {
					crossingCar = CarGeneration.eastQueue.peek();
					eastCrossing = CarGeneration.eastQueue.peek().cTime;

					System.out.printf("Car %d has entered the intersection heading %c at %d\n", crossingCar.id,
							crossingCar.dir, currentTime);

				}
			} else if (eastCrossing != 0) {
				eastCrossing--;
				if (eastCrossing == 0) {
					crossingCar = CarGeneration.eastQueue.peek();

					System.out.printf("Car %d has crossed the intersection heading %c at %d\n", crossingCar.id,
							crossingCar.dir, currentTime);
					tempWait = currentTime - crossingCar.aTime;
					eastWait += tempWait;
					if (tempWait > eastLongestWaitTime) {
						eastLongestWaitTime = tempWait;
						eastLongestWaitID = crossingCar.id;
					}
					CarGeneration.eastQueue.remove();

				}
			}

			if (elGreen == true && westCrossing == 0) {

				if (!CarGeneration.westQueue.isEmpty() && CarGeneration.westQueue.peek().aTime <= currentTime) {
					crossingCar = CarGeneration.westQueue.peek();
					westCrossing = CarGeneration.westQueue.peek().cTime;

					System.out.printf("Car %d has entered the intersection heading %c at %d\n", crossingCar.id,
							crossingCar.dir, currentTime);

				}
			} else if (westCrossing != 0) {
				westCrossing--;
				if (westCrossing == 0) {
					crossingCar = CarGeneration.westQueue.peek();

					System.out.printf("Car %d has crossed the intersection heading %c at %d\n", crossingCar.id,
							crossingCar.dir, currentTime);
					tempWait = currentTime - crossingCar.aTime;
					westWait += tempWait;
					if (tempWait > westLongestWaitTime) {
						westLongestWaitTime = tempWait;
						westLongestWaitID = crossingCar.id;
					}
					CarGeneration.westQueue.remove();

				}
			}

			currentTime++;
		}

		northAvg = northWait / northCars;
		eastAvg = eastWait / eastCars;
		southAvg = southWait / southCars;
		westAvg = westWait / westCars;
	
		
		System.out.printf("%d cars travelled North\n", northCars);
		System.out.printf("The average wait time for North was %d seconds.\n", northAvg);
		System.out.printf("Car %d waited %d seconds, the longest in the North lane.\n", northLongestWaitID,
				northLongestWaitTime);

		System.out.printf("%d cars travelled South\n", southCars);
		System.out.printf("The average wait time for South was %d seconds.\n", southAvg);
		System.out.printf("Car %d waited %d seconds, the longest in the South lane.\n", southLongestWaitID,
				southLongestWaitTime);

		System.out.printf("%d cars travelled East\n", eastCars);
		System.out.printf("The average wait time for East was %d seconds.\n", eastAvg);
		System.out.printf("Car %d waited %d seconds, the longest in the East lane.\n", eastLongestWaitID,
				eastLongestWaitTime);

		System.out.printf("%d cars travelled West\n", westCars);
		System.out.printf("The average wait time for West was %d seconds.\n", westAvg);
		System.out.printf("Car %d waited %d seconds, the longest in the West lane.\n", westLongestWaitID,
				westLongestWaitTime);

	}
}
