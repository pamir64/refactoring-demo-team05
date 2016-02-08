package com.scrumtrek.simplestore;

import javax.annotation.Generated;

public class Rental {
	private Movie m_Movie;
	private int m_DaysRented;

	public Rental(Movie movie, int daysRented) {
		m_Movie = movie;
		m_DaysRented = daysRented;
	}

	public int getDaysRented() {
		return m_DaysRented;
	}

	public Movie getMovie() {
		return m_Movie;
	}
	
	public double getAmount(){
		return m_Movie.getPriceCode().getAmount(m_DaysRented);
	}
	
	public int getBonus() {
		return m_Movie.getPriceCode().getBonus(m_DaysRented);
	}
}

