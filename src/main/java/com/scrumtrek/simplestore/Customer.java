package com.scrumtrek.simplestore;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private String m_Name;
	private List<Rental> m_Rentals = new ArrayList<Rental>();

	public List<Rental> getRentals() {
		return m_Rentals;
	}

	public Customer(String name) {
		m_Name = name;
	}

	public String getName() {
		return m_Name;
	}


	public void addRental(Rental arg){
		m_Rentals.add(arg);
	}
	
	private String text;

	public String Statement()
	{
		
		Reporter rep = new Reporter(this) {
			
			@Override
			public void out(String statment) {
				text= statment;
				System.out.println(statment);
			}
		};
		return text;
	}
}

