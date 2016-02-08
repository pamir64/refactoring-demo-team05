package com.scrumtrek.simplestore;

public abstract class PriceCode {
	
	private String code;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public abstract double getAmount(int days);
	
	public abstract int getBonus(int days);

}
