package com.siwoo.workshop.one.model;


/**
 * Created by Siwoo Kim on 2018-10-08
 */

public class Meal {
    public static final double TAX = 0.13;
    public static final double TIP = 0.15;

    private final double cost;

    public Meal(double cost) {
        this.cost = cost;
    }

    public double getTotal() {
        if(cost <= 0) {
            return 0;
        }
        return includingTip(includingTax(cost));
    }

    public static double includingTax(double cost) {
        return cost * TAX + cost;
    }


    public static double includingTip(double cost) {
        return cost * TIP + cost;
    }

}
