package trafficlights;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Simulator {

	static PriorityQueue<Event> eventQ = new PriorityQueue<>();

	public static void main(String[] args) throws Exception {

		CarGeneration.main(null);
		int startTime;
		int endTime;
		int changeFrequency;
		boolean nlGreen = true;
		boolean elGreen = false;
		int currentTime;
		int lightChange;
		int crossTime;

		LinkedList<Car> crossingQueue = new LinkedList<>();

		// Takes input from the user for start time, end time, and light change
		// frequency
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

		elGreen = false;
		nlGreen = true;

		while (!eventQ.isEmpty()) {
			Car crossingCar;
			Car newArrival;
			currentTime = current.getTime();
			current = eventQ.remove();
			switch (current.getEventType()) {
			/*
			 * case "NG":
			 * 
			 * elGreen = false; nlGreen = true;
			 * 
			 * crossTime = northQueue.peek().cTime;
			 * 
			 * if(lightChange > currentTime + crossTime) { Event crossing = new Event("C",
			 * currentTime + crossTime); eventQ.add(crossing);
			 * 
			 * } break;
			 * 
			 * case "EG":
			 * 
			 * elGreen = true; nlGreen = false; crossTime = eastQueue.peek().cTime; while
			 * (elGreen == true) { // crossingCar = eastQueue.remove(); Event crossing = new
			 * Event("C", currentTime + crossingCar.cTime ); currentTime +=
			 * crossingCar.cTime; eventQ.add(crossing);
			 * 
			 * }
			 * 
			 * 
			 * break;
			 */
			case "C":
				if (nlGreen == true && !CarGeneration.northQueue.isEmpty()) {
					crossingCar = CarGeneration.northQueue.remove();
					System.out.printf("Car %d has crossed the intersection heading %c at %d\n", crossingCar.id,
							crossingCar.dir, currentTime);
					currentTime += crossingCar.cTime;
				}
				if (nlGreen == true && !CarGeneration.southQueue.isEmpty()) {
					crossingCar = CarGeneration.southQueue.remove();
					System.out.printf("Car %d has crossed the intersection heading %c at %d\n", crossingCar.id,
							crossingCar.dir, currentTime);
					currentTime += crossingCar.cTime;
				} else if (elGreen == true && !CarGeneration.eastQueue.isEmpty()) {
					crossingCar = CarGeneration.eastQueue.remove();
					System.out.printf("Car %d has crossed the intersection heading %c at %d\n", crossingCar.id,
							crossingCar.dir, currentTime);

					currentTime += crossingCar.cTime;
				}
				if (elGreen == true && !CarGeneration.westQueue.isEmpty()) {
					crossingCar = CarGeneration.westQueue.remove();
					System.out.printf("Car %d has crossed the intersection heading %c at %d\n", crossingCar.id,
							crossingCar.dir, currentTime);
					currentTime += crossingCar.cTime;
				}

				break;
			case "A":

				lightChange += changeFrequency;
				if (nlGreen == true) {

					int nArrival = CarGeneration.northQueue.peek().aTime;
					int ncross = CarGeneration.northQueue.peek().cTime;

					int sArrival = CarGeneration.southQueue.peek().aTime;
					int scross = CarGeneration.southQueue.peek().cTime;
					if (lightChange > nArrival) {
						if (currentTime < nArrival) {
							currentTime = nArrival + ncross;

							Event crossing = new Event("C", currentTime);
							eventQ.add(crossing);
						} else if (currentTime > nArrival) {

						}
					} else if (lightChange < nArrival) {
						nlGreen = false;
						elGreen = true;
						lightChange += changeFrequency;

					}

					if (lightChange > sArrival) {
						if (currentTime < sArrival) {
							currentTime = sArrival + scross;

							Event crossing = new Event("C", currentTime);
							eventQ.add(crossing);
						} else if (currentTime > sArrival) {

						}
					} else if (lightChange < sArrival) {
						nlGreen = false;
						elGreen = true;
						lightChange += changeFrequency;

					}

				}

				else if (elGreen == true) {
					int eArrival = CarGeneration.eastQueue.peek().aTime;
					int eCross = CarGeneration.eastQueue.peek().cTime;

					int wArrival = CarGeneration.westQueue.peek().aTime;
					int wCross = CarGeneration.westQueue.peek().cTime;

					if (lightChange > eArrival + eCross) {
						currentTime = eArrival + eCross;
						Event crossing = new Event("C", currentTime);
						eventQ.add(crossing);
					} else if (lightChange < eArrival + eCross) {
						nlGreen = true;
						elGreen = false;

					}

					if (lightChange > wArrival + wCross) {
						currentTime = wArrival + wCross;
						Event crossing = new Event("C", currentTime);
						eventQ.add(crossing);
					} else if (lightChange < wArrival + wCross) {
						nlGreen = true;
						elGreen = false;

					}
				}

				/*
				 * newArrival = CarGeneration.carQueue.remove();
				 * 
				 * if(newArrival.dir == 'N') { CarGeneration.northQueue.add(newArrival);
				 * System.out.printf("Car %d has arrived in the %C queue at %d\n",
				 * newArrival.id,newArrival.dir, newArrival.aTime); crossTime =
				 * CarGeneration.northQueue.peek().cTime;
				 * 
				 * if(nlGreen == true && lightChange > currentTime + crossTime) { Event crossing
				 * = new Event("C", currentTime + crossTime); crossingQueue.add(newArrival);
				 * eventQ.add(crossing);
				 * 
				 * } } if(newArrival.dir == 'S') { CarGeneration.southQueue.add(newArrival);
				 * System.out.printf("Car %d has arrived in the %C queue at %d\n",
				 * newArrival.id,newArrival.dir, newArrival.aTime);
				 * 
				 * }if(newArrival.dir == 'E') { CarGeneration.eastQueue.add(newArrival);
				 * System.out.printf("Car %d has arrived in the %C queue at %d\n",
				 * newArrival.id,newArrival.dir, newArrival.aTime);
				 * 
				 * }if(newArrival.dir == 'W') { CarGeneration.westQueue.add(newArrival);
				 * System.out.printf("Car %d has arrived in the %C queue at %d\n",
				 * newArrival.id,newArrival.dir, newArrival.aTime);
				 * 
				 * }
				 * 
				 * break;
				 */
			}

		}

	}

}
