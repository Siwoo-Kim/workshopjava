package com.siwoo.workshop.one;

import com.siwoo.workshop.one.model.Meal;

import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * Created by Siwoo Kim on 2018-10-08
 */

public class MealProgram {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your price for meal.");
            Meal meal = null;
            try {
                double price = scanner.nextDouble();
                meal = new Meal(price);
            }catch (InputMismatchException e) {
                System.out.println("Enter only number.");
                continue;
            }

            System.out.println("Total price: " + meal.getTotal());
        }
    }
}
