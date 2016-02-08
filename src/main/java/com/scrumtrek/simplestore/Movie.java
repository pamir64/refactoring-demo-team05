package com.scrumtrek.simplestore;

public class Movie {
	private String m_Title;
	private PriceCode m_PriceCode;

	public Movie(String title, PriceCode priceCode) {
		m_Title = title;
		m_PriceCode = priceCode;
	}

	public PriceCode getPriceCode()	{
		return m_PriceCode;
	}
	
	public void setPriceCode(PriceCode value) {
		m_PriceCode = value;
	}

	public String getTitle() {
		return m_Title;
	}
}

