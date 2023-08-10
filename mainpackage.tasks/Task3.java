package mainpackage.tasks;

import java.util.ArrayList;
import java.util.Scanner;

import mainpackage.customer.PermCustomer;

public class Task3 {
	
	public static ArrayList<Integer> getInts(PermCustomer permCustomer) {
		ArrayList<Integer> requests = new ArrayList<Integer>();
		requests.add(permCustomer.getMonReq());
		requests.add(permCustomer.getTueReq());
		requests.add(permCustomer.getWedReq());
		requests.add(permCustomer.getThuReq());
		requests.add(permCustomer.getFriReq());
		requests.add(permCustomer.getSatReq());
		requests.add(permCustomer.getSunReq());
		return requests;
	}
	
	public static ArrayList<Double> getBinomDist(PermCustomer permCustomer) {
		ArrayList<Double> binomdist = new ArrayList<Double>();
		for(int i = 1; i <= 6; i++) {
			binomdist.add(binomialRow(7)[i]*Math.pow(0.5, 7));
		}
		return binomdist;
	}
	
	public static int[] binomialRow(int n) {
        int[] row = new int[n + 1];
        row[0] = 1;
        for (int i = 1; i <= n; i++)
            for (int j = i; j >= 0; j--)
                row[j] = (j == 0 ? 0 : row[j - 1]) + row[j];
        return row;
    }
	
	public static void displayUnusuality(PermCustomer permCustomer) {
		for(int i = 1; i <= 6; i++) {
			System.out.print("Unusuality of requests for "+i+" days being below or above average is ");
			if(Math.abs((2*i-7)/Math.sqrt(7)) < 2) {
				System.out.println("not unusual with z-score of "+(2*i-7)/Math.sqrt(7));
			} else if(Math.abs((2*i-7)/Math.sqrt(7)) > 2 || Math.abs((2*i-7)/Math.sqrt(7)) < 3) {
				System.out.println("somewhat unusual with z-score of "+(2*i-7)/Math.sqrt(7));
			} else {
				System.out.println("very unusual (outlier) with z-score of "+(2*i-7)/Math.sqrt(7));
			}
		}
		ArrayList<Integer> reqs = getInts(permCustomer);
		int value = 0;
		int occurence = 0;
		for(int i = 0; i <= 6; i++) {
			value += reqs.get(i);
		}
		if(value % 7 == 0) {
			value /= 7;
			for(int i = 0; i <= 6; i++) {
				if(value == reqs.get(i)) {
					return;
				} else if(value > reqs.get(i)) {
					occurence++;
				}
			}
		} else {
			double newvalue = value/7.0;
			for(int i = 0; i <= 6; i++) {
				if(newvalue > reqs.get(i)) {
					occurence++;
				}
			}
		}
		ArrayList<Double> binomdist = getBinomDist(permCustomer);
		System.out.print("Your requests have "+occurence+" days below average and "+(7-occurence)+" days above average. ");
		System.out.println("Probability of that is: "+binomdist.get(occurence-1));
	}
	
	public static void displayCumulativeProbability(PermCustomer permCustomer) {
		ArrayList<Double> binomdist = getBinomDist(permCustomer);
		Scanner scanner = new Scanner(System.in);
		boolean duable;
		int numofdays = 0;
		System.out.println("Enter how many days of week (between 1 and 6):");
		do {
		if(scanner.hasNextInt()) {
			numofdays = scanner.nextInt();
			if(numofdays >= 1 && numofdays <= 6) {
				duable = true;
			} else {
				duable = false;System.out.println("Enter valid integer (between 1 and 6):");
			}
		}
		else {duable = false;System.out.println("Enter valid integer (between 1 and 6):");}
		} while(!duable);
		int validintcount = 0;
		ArrayList<Integer> days = new ArrayList<Integer>();
		System.out.println("Enter for how many days requests being below or above average (between 1 and 6):");
		while(validintcount < numofdays) {
		do {
		if(scanner.hasNextInt()) {
			days.add(scanner.nextInt());
			if(days.get(days.size()-1) >= 1 && days.get(days.size()-1) <= 6) {
				duable = true;validintcount++;
			} else {
				days.remove(days.size()-1);duable = false;System.out.println("Enter valid integer (between 1 and 6):");
			}
		}
		else {duable = false;System.out.println("Enter valid integer (between 1 and 6):");}
		} while(!duable); }
		double value = 0;
		for(int i = 1; i <= numofdays; i++) {
			value += binomdist.get(days.remove(0)-1);
		}
		System.out.println("Total probability is "+value);
		scanner.close();
	}
}
