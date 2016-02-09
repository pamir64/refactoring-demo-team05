package com.scrumtrek.simplestore;

public abstract class Reporter {
	
	private String statment;
	
	private String getReport(Customer customer){
		double totalAmount = 0;
		int frequentRenterPoints = 0;
				
		String result = "Rental record for " + customer.getName() + "\n";
		
		for(Rental each: customer.getRentals()) {
		
			double thisAmount = each.getAmount();

			// Add frequent renter points
			frequentRenterPoints++;

			frequentRenterPoints += each.getBonus();
			// Show figures for this rental
			result += "\t" + each.getMovie().getTitle() + "\t" + thisAmount + "\n";
			totalAmount += thisAmount;
		}

		// Add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points.";
		return result;
	}
	
	public abstract void out(String statment);
	
	public Reporter(Customer customer){
		statment = getReport(customer);
		out(statment);
	}
	
	private Reporter(){}

}
