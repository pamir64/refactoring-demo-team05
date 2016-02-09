package com.scrumtrek.simplestore;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.scrumtrek.simplestore.price.Childrens;
import com.scrumtrek.simplestore.price.NewRelease;
import com.scrumtrek.simplestore.price.Regular;

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
    public void testPrice() throws Exception {
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
    

    @Test
    public void testWithRegularPrice() throws Exception {
        String name = "test1";
        Customer c1 = new Customer(name);
        Regular reg = new Regular();

        c1.addRental(new Rental(new Movie("movie-name", reg), 10));

        String statement = c1.Statement();
        assertNotNull(statement);

        assertEquals(
                "Rental record for " + name + "\n"
                + "	movie-name	14.0\n"
                + "Amount owed is 14.0\n"
                + "You earned 1 frequent renter points.", statement);
    }

    @Test
    public void testWithRegularPriceDaysRented2() throws Exception {
        String name = "test1";
        Customer c1 = new Customer(name);
        Regular reg = new Regular();

        c1.addRental(new Rental(new Movie("movie-name", reg), 2));

        String statement = c1.Statement();
        assertNotNull(statement);

        assertEquals(
                "Rental record for " + name + "\n"
                + "	movie-name	2.0\n"
                + "Amount owed is 2.0\n"
                + "You earned 1 frequent renter points.", statement);
    }

    @Test
    public void testWithNewReleaseWithBonus() throws Exception {
        String name = "test1";
        Customer c1 = new Customer(name);
        NewRelease newR = new NewRelease(); 

        c1.addRental(new Rental(new Movie("movie-name", newR), 5));

        String statement = c1.Statement();
        assertNotNull(statement);

        assertEquals(
                "Rental record for " + name + "\n"
                + "	movie-name	15.0\n"
                + "Amount owed is 15.0\n"
                + "You earned 2 frequent renter points.", statement);
    }

    @Test
    public void testWithNewReleaseWithoutBonus() throws Exception {
        String name = "test1";
        Customer c1 = new Customer(name);
        NewRelease newR = new NewRelease();

        c1.addRental(new Rental(new Movie("movie-name", newR), 1));

        String statement = c1.Statement();
        assertNotNull(statement);

        assertEquals(
                "Rental record for " + name + "\n"
                + "	movie-name	3.0\n"
                + "Amount owed is 3.0\n"
                + "You earned 1 frequent renter points.", statement);
    }

    @Test
    public void testWithChildrens() throws Exception {
        String name = "test1";
        Customer c1 = new Customer(name);
        Childrens ch = new Childrens();

        c1.addRental(new Rental(new Movie("movie-name", ch), 15));

        String statement = c1.Statement();
        assertNotNull(statement);

        assertEquals(
                "Rental record for " + name + "\n"
                + "	movie-name	18.0\n"
                + "Amount owed is 18.0\n"
                + "You earned 1 frequent renter points.", statement);
    }

    @Test
    public void testWithChildrensDaysRented3() throws Exception {
        String name = "test1";
        Customer c1 = new Customer(name);
        Childrens ch = new Childrens();

        c1.addRental(new Rental(new Movie("movie-name", ch), 3));

        String statement = c1.Statement();
        assertNotNull(statement);

        assertEquals(
                "Rental record for " + name + "\n"
                + "	movie-name	1.5\n"
                + "Amount owed is 1.5\n"
                + "You earned 1 frequent renter points.", statement);
    }
}