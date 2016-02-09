package com.scrumtrek.simplestore;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

import com.scrumtrek.simplestore.price.Xxx;
import com.scrumtrek.simplestore.reports.HtmlReporter;
import com.scrumtrek.simplestore.reports.PlainReporter;
import org.junit.Assert;
import org.junit.Test;

import com.scrumtrek.simplestore.price.Childrens;
import com.scrumtrek.simplestore.price.NewRelease;
import com.scrumtrek.simplestore.price.Regular;

public class ReportTest {

    @Test
    public void emptyTest() throws Exception {
        String name = "empty";
        Customer empty = new Customer(name);

        String statement = new PlainReporter().getReport(empty);
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

        String statement = new PlainReporter().getReport(c1);
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

        String statement = new PlainReporter().getReport(c1);
        assertNotNull(statement);

        assertEquals(
                "Rental record for " + name + "\n"
                + "	movie-name	14.0\n"
                + "Amount owed is 14.0\n"
                + "You earned 1 frequent renter points.", statement);
    }

    @Test
    public void testWithTwoRegularPrice() throws Exception {
        String name = "test1";
        Customer c1 = new Customer(name);
        Regular reg = new Regular();

        Rental rental = new Rental(new Movie("movie-name", reg), 10);
        rental.addMovie(new Movie("movie-name2", reg));
        c1.addRental(rental);

        String statement = new PlainReporter().getReport(c1);
        assertNotNull(statement);

        assertEquals(
                "Rental record for test1\n" +
                "\tmovie-name\t14.0\n" +
                "\tmovie-name2\t14.0\n" +
                "Amount owed is 28.0\n" +
                "You earned 1 frequent renter points.", statement);
    }

    @Test
    public void testWithRegularPriceDaysRented2() throws Exception {
        String name = "test1";
        Customer c1 = new Customer(name);
        Regular reg = new Regular();

        c1.addRental(new Rental(new Movie("movie-name", reg), 2));

        String statement = new PlainReporter().getReport(c1);
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

        String statement = new PlainReporter().getReport(c1);
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

        String statement = new PlainReporter().getReport(c1);
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

        String statement = new PlainReporter().getReport(c1);
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

        String statement = new PlainReporter().getReport(c1);
        assertNotNull(statement);

        assertEquals(
                "Rental record for " + name + "\n"
                + "	movie-name	1.5\n"
                + "Amount owed is 1.5\n"
                + "You earned 1 frequent renter points.", statement);
    }

    @Test
    public void testXxxDaysAmount() {
        PriceCode xxx = new Xxx();
        double amount4 = xxx.getAmount(4);
        Assert.assertEquals(2, amount4, 0.0001);
        double amount5 = xxx.getAmount(5);
        Assert.assertEquals(2, amount5, 0.0001);
        double amount6 = xxx.getAmount(6);
        Assert.assertEquals(3.6, amount6, 0.0001);
    }

    @Test
    public void testXxxDaysBonus() {
        PriceCode xxx = new Xxx();
        int bonus4 = xxx.getBonus(4);
        Assert.assertEquals(0, bonus4);
        int bonus5 = xxx.getBonus(5);
        Assert.assertEquals(0, bonus5);
        int bonus6 = xxx.getBonus(6);
        Assert.assertEquals(0, bonus6);
    }

    @Test
    public void htmlReportTest() {
        String name = "test1";
        Customer c1 = new Customer(name);
        Childrens ch = new Childrens();

        c1.addRental(new Rental(new Movie("movie-name", ch), 3));
        String statement = new HtmlReporter().getReport(c1);
        Assert.assertEquals("<html><head><title>Rental record for test1</title></head>" +
                "<body><p>movie-name: 1.5<p>Amount owed is 1.5<br>You earned 1 frequent renter points.</body></html>"
                , statement
        );
    }
}