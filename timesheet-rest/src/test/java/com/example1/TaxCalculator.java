package com.example1;

public class TaxCalculator {
    private final TaxResolver taxResolver;

    public TaxCalculator(TaxResolver taxResolver) {
        this.taxResolver = taxResolver;
    }

    public double getPriceWithTax(double price) {
        double currentTax = taxResolver.getCurrentTax();
        return price + price * currentTax;
    }
}
