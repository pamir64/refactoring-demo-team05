package com.scrumtrek.simplestore.price;

import com.scrumtrek.simplestore.PriceCode;

public class Childrens extends PriceCode{

	@Override
	public double getAmount(int days) {
		return (days>3)?(days - 3) * 1.5:1.5;
	}

	@Override
	public int getBonus(int days) {
		return 0;
	}

}
