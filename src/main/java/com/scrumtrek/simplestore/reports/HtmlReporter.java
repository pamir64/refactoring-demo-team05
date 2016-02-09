package com.scrumtrek.simplestore.reports;

import com.scrumtrek.simplestore.Customer;
import com.scrumtrek.simplestore.Movie;
import com.scrumtrek.simplestore.Rental;

public class HtmlReporter implements Reporter {

    @Override
    public String getReport(Customer customer) {

        double totalAmount = 0;
        int frequentRenterPoints = 0;

        StringBuilder report = new StringBuilder("<html>");
        report
                .append("<head><title>")
                .append("Rental record for " + customer.getName())
                .append("</title>")
                .append("</head>");

        report.append("<body>");
        for(Rental each: customer.getRentals()) {

            double thisAmount = each.getAmount();

            // Add frequent renter points
            frequentRenterPoints++;

            frequentRenterPoints += each.getBonus();
            // Show figures for this rental
            report.append("<p>");
            for (Movie movie : each.getMovies()) {

                report.append(movie.getTitle());
            }
            report.append(": ").append(thisAmount);
            totalAmount += thisAmount;
        }

        // Add footer lines
        report
                .append("<p>")
                .append("Amount owed is ").append(totalAmount)
                .append("<br>")
                .append("You earned ").append(frequentRenterPoints).append(" frequent renter points.")
                .append("</body>")
                .append("</html>");
        return report.toString();
    }
}
