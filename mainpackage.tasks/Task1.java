package mainpackage.tasks;

import java.util.ArrayList;

import mainpackage.customer.PermCustomer;

public class Task1 {
	
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
	
	public static double calculateAverage(PermCustomer permCustomer) {
		ArrayList<Integer> requests = getInts(permCustomer);
		int value = 0;
		for (int i = 0; i <= 6; i++) {
			value += requests.get(i);
		}
		return value/7.0;
	}
	
	public static double calculateCovariance(PermCustomer permCustomer) {
		ArrayList<Integer> requests = getInts(permCustomer);
		double value = -24.5*calculateAverage(permCustomer);
		for (int i = 0; i <= 6; i++) {
			value += (requests.get(i)*(i+0.5));
		}
		return value/6.0;
	}
	
	public static double calculateReqVariance(PermCustomer permCustomer) {
		ArrayList<Integer> requests = getInts(permCustomer);
		int value = 0;
		for (int i = 0; i <= 6; i++) {
			value += (requests.get(i)-calculateAverage(permCustomer))*(requests.get(i)-calculateAverage(permCustomer));
		}
		return value/6.0;
	}
	
	public static double calculateCorrCoef(PermCustomer permCustomer) {
		return calculateCovariance(permCustomer)/Math.sqrt(14*calculateReqVariance(permCustomer)/3);
	}
	
	public static double calculateSlope(PermCustomer permCustomer) {
		return 3*calculateCovariance(permCustomer)/14;
	}
	
	public static double calculateYIntercept(PermCustomer permCustomer) {
		return calculateAverage(permCustomer)-3.5*calculateSlope(permCustomer);
	}
	
	public static void displayDay(int i) {
		switch(i) {
		case 0:
			System.out.print("On Monday");
			break;
		case 1:
			System.out.print("On Tuesday");
			break;
		case 2:
			System.out.print("On Wednesday");
			break;
		case 3:
			System.out.print("On Thursday");
			break;
		case 4:
			System.out.print("On Friday");
			break;
		case 5:
			System.out.print("On Saturday");
			break;
		case 6:
			System.out.print("On Sunday");
			break;
		}
	}
	
	public static double calculateRelativePercentError(int i, PermCustomer permCustomer) {
		ArrayList<Integer> requests = getInts(permCustomer);
		return 100*Math.abs(requests.get(i)-Math.round((i+0.5)*calculateSlope(permCustomer)+calculateYIntercept(permCustomer)))/requests.get(i);
	}
	
	public static void displayAnalytics(PermCustomer permCustomer) {
		ArrayList<Integer> requests = getInts(permCustomer);
		for (int i = 0; i <= 6; i++) {
			displayDay(i);
			System.out.print(" there were "+requests.get(i)+" requests");
			System.out.print(" which are expected to be "+Math.round((i+0.5)*calculateSlope(permCustomer)+calculateYIntercept(permCustomer)));
			System.out.println(" with a relative percent error of "+calculateRelativePercentError(i, permCustomer));
		}
		if(calculateCorrCoef(permCustomer) > 0) {
			System.out.println("Relationship is positive with a correlation coefficient of "+calculateCorrCoef(permCustomer));
		} else {
			System.out.println("Relationship is negative with a correlation coefficient of "+calculateCorrCoef(permCustomer));
		}
	}
	
}