package com.scrumtrek.simplestore;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ReportTest {

    @Test
    public void emptyTest() throws Exception {
        String name = "empty";
        Customer empty = new Customer(name);

        String statement = empty.Statement();
        assertNotNull(statement);

        assertEquals(
                "Rental record for " + name + "\n"
                + "Amount owed is 0.0\n"
                + "You earned 0 frequent renter points.", statement);
    }

    @Test
    public void testWithRegularPrice() throws Exception {
        String name = "test1";
        Customer c1 = new Customer(name);

        PriceCode priceCode = new PriceCode() {
			
			@Override
			public int getBonus(int days) {
				return 0;
			}
			
			@Override
			public double getAmount(int days) {
				return 0;
			}
		};
        c1.addRental(new Rental(new Movie("movie-name", priceCode), 10));

        String statement = c1.Statement();
        assertNotNull(statement);

        assertEquals(
                "Rental record for " + name + "\n"
                + "	movie-name	0.0\n"
                + "Amount owed is 0.0\n"
                + "You earned 1 frequent renter points.", statement);
    }
}