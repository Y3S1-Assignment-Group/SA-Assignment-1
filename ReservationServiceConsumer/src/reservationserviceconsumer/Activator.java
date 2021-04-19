package reservationserviceconsumer;

import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import resturantservicepublisher.ResturantServicePublish;

public class Activator implements BundleActivator {

	ServiceReference serviceReference;
	
	public void start(BundleContext context) throws Exception {
		System.out.println("Start Subscriber Service");
		serviceReference = context.getServiceReference(ResturantServicePublish.class.getName());
		@SuppressWarnings("unchecked")
		ResturantServicePublish resturantServicePublish = (ResturantServicePublish)context.getService(serviceReference);	
		displayMainMenu(resturantServicePublish);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Good Bye!");
		context.ungetService(serviceReference);
	}
	
	public void displayMainMenu(ResturantServicePublish resturantServicePublish) {
		
		int option;
		String suboption = "y";
		
		Scanner sc = new Scanner(System.in);
		System.out.println("\n\n");
		System.out.println("----------Imeero Family Restaurant - Reservation ----------");
		System.out.println("1  - Add Reservation");
		System.out.println("2  - Get all Reservations");
		System.out.println("3  - Get reservation by name");
		System.out.println("4  - Delete reservation");
		System.out.println("5  - Get priority/normal reservations");

		
		System.out.print("Choose an option : ");
		
		option = Integer.parseInt(sc.nextLine().trim());
		
		switch(option) {
			case 1:
				resturantServicePublish.insertReservation();
				
				while(suboption.equals("y")) {
					System.out.println("\n\nDo you want to Add Another Reservation(y/n)");
					suboption = sc.nextLine().trim();
		
					if(suboption.equals("y")) {
						resturantServicePublish.insertReservation();
					}
				}
				displayMainMenu(resturantServicePublish);
				break;
			case 2:
				resturantServicePublish.getallReservations();
				displayMainMenu(resturantServicePublish);
				break;
			case 3:
				resturantServicePublish.getReservationsByCustName();
				displayMainMenu(resturantServicePublish);
				break;
			case 4:
				resturantServicePublish.deleteReservation();
				displayMainMenu(resturantServicePublish);
				break;
			case 5:
				resturantServicePublish.getPriorityNormal();
				displayMainMenu(resturantServicePublish);
				break;
			default:
				System.out.println("Incorrect Input. Try Again...");
				displayMainMenu(resturantServicePublish);
		}
		
		
	}



}