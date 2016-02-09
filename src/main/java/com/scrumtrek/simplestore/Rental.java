package com.scrumtrek.simplestore;

import java.util.ArrayList;
import java.util.List;

public class Rental {
	private List<Movie> movies;
	private int m_DaysRented;

	public Rental(Movie movie, int daysRented) {
		movies = new ArrayList<Movie>();
        addMovie(movie);
		m_DaysRented = daysRented;
	}

	public int getDaysRented() {
		return m_DaysRented;
	}

	public List<Movie> getMovies() {
		return movies;
	}

    public boolean addMovie(Movie movie) {
        return movies.add(movie);
    }

    public double getAmount() {
        double sum = 0.;
        for (Movie movie : movies) {
            sum += movie.getPriceCode().getAmount(m_DaysRented);
        }
        return sum;
    }

    public int getBonus() {
        int sum = 0;
        for (Movie movie : movies) {
            sum += movie.getPriceCode().getBonus(m_DaysRented);
        }
        return sum;
	}
}

