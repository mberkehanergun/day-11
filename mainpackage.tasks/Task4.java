package mainpackage.tasks;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.math3.special.Erf;

import mainpackage.customer.PermCustomer;

public class Task4 {
	
	public static ArrayList<Double> getBinomDist(PermCustomer permCustomer) {
		ArrayList<Double> binomdist = new ArrayList<Double>();
		double value = 1;
		for(int i = 1; i <= 27; i++) {
			value *= binomialRow(28)[i];
			for(int j = 1; j <= 28; j++) {
				value *= 0.5;
			}
			binomdist.add(value);
			value = 1;
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
	
	public static double getProbBetween(double x, double y) {
		return -0.5*Erf.erf(3.74166-0.267261*y)+0.5*Erf.erf(3.74166-0.267261*x);
	}
	
	public static double getProbBiggerThan(double x) {
		return 0.5+0.5*Erf.erf(3.74166-0.267261*x);
	}
	
	public static double getProbSmallerThan(double y) {
		return -0.5*Erf.erf(3.74166-0.267261*y)+0.5;
	}
	
	public static void displayUnusuality(PermCustomer permCustomer) {
		for(int i = 1; i <= 27; i++) {
			System.out.print("Unusuality of requests for "+i+" days being below or above average is ");
			if(Math.abs((i-14)/Math.sqrt(7)) < 2) {
				System.out.println("not unusual with z-score of "+(i-14)/Math.sqrt(7));
			} else if(Math.abs((i-14)/Math.sqrt(7)) > 2 && Math.abs((i-14)/Math.sqrt(7)) < 3) {
				System.out.println("somewhat unusual with z-score of "+(i-14)/Math.sqrt(7));
			} else {
				System.out.println("very unusual (outlier) with z-score of "+(i-14)/Math.sqrt(7));
			}
		}
		ArrayList<Integer> reqs = Task2.predictReqs(permCustomer);
		int value = 0;
		int occurence = 0;
		for(int i = 0; i <= 27; i++) {
			value += reqs.get(i);
		}
		if(value % 28 == 0) {
			value /= 28;
			for(int i = 0; i <= 27; i++) {
				if(value == reqs.get(i)) {
					return;
				} else if(value > reqs.get(i)) {
					occurence++;
				}
			}
		} else {
			double newvalue = value/28.0;
			for(int i = 0; i <= 27; i++) {
				if(newvalue > reqs.get(i)) {
					occurence++;
				}
			}
		}
		ArrayList<Double> binomdist = getBinomDist(permCustomer);
		System.out.print("Your requests have "+occurence+" days below average and "+(28-occurence)+" days above average. ");
		System.out.println("Probability of that is: "+binomdist.get(occurence-1));
	}
	
	public static void displayProbability(PermCustomer permCustomer) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter number of arguments (1 or 2):");
		int num = scanner.nextInt();
		scanner.nextLine();
		if(num == 1) {
			System.out.println("Enter direction (Type bigger or smaller):");
			String input = scanner.nextLine();
			if(input.equals("bigger")) {
				System.out.println("Enter floating point number:");
				double a = scanner.nextDouble();
				scanner.nextLine();
				System.out.println("Probability is: "+getProbBiggerThan(a));
			} else if(input.equals("smaller")) {
				System.out.println("Enter floating point number:");
				double b = scanner.nextDouble();
				scanner.nextLine();
				System.out.println("Probability is: "+getProbSmallerThan(b));
			}
		} else if(num == 2) {
			System.out.println("Enter lower bound floating point number:");
			double x = scanner.nextDouble();
			scanner.nextLine();
			System.out.println("Enter upper bound floating point number:");
			double y = scanner.nextDouble();
			scanner.nextLine();
			System.out.println("Probability is: "+getProbBetween(x, y));
		}
		scanner.close();
	}
	
}
