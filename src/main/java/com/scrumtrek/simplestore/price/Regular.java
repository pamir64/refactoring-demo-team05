package com.scrumtrek.simplestore.price;

import com.scrumtrek.simplestore.PriceCode;

public class Regular extends PriceCode{

	@Override
	public double getAmount(int days) {
		return (days>2)?(days - 2) * 1.5+2:2;
	}

	@Override
	public int getBonus(int days) {
		return 0;
	}

}
