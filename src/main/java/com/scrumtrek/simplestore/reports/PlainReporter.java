package com.scrumtrek.simplestore.reports;

import com.scrumtrek.simplestore.Customer;
import com.scrumtrek.simplestore.Rental;

public class PlainReporter implements Reporter {
	
	@Override
	public String getReport(Customer customer){
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
}
