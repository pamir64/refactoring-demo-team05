package com.scrumtrek.simplestore.price;

import com.scrumtrek.simplestore.PriceCode;

public class Xxx extends PriceCode {

    private final double basePrice = 2;
    private final int discountDays = 5;
    private final double discount = 0.8;

    @Override
    public double getAmount(int days) {
        return (Math.max(days - discountDays, 0) * discount + 1) * basePrice;
        // заказчик путался в показаниях, так что оставим пока его первое видение прайса
//        return (Math.max(days - discountDays, 0) * 0.8 + Math.min(days, discountDays)) * basePrice;
    }

    @Override
    public int getBonus(int days) {
        return 0;
    }
}
