package com.scrumtrek.simplestore.price;

import com.scrumtrek.simplestore.PriceCode;

public class NewRelease extends PriceCode{

	@Override
	public double getAmount(int days) {
		return days * 3;
	}

	@Override
	public int getBonus(int days) {
		// TODO Auto-generated method stub
		return (days > 1)?1:0;
	}

}
