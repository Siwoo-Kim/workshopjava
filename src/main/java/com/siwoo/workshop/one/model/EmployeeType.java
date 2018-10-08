package com.siwoo.workshop.one.model;

import com.siwoo.workshop.one.EmployeeProgram;

/**
 * Created by sm123tt@gmail.com on 2018-10-08
 * Project : workshop1
 * Github : http://github.com/Siwoo-Kim
 */

public enum EmployeeType {
    SENIOR(10, null, 20.), INTERMEDIATE(3, SENIOR, 19.), JUNIOR(0, INTERMEDIATE, 17.);
    private final int MAX_YEAR;
    private EmployeeType nextLevel;
    private final double hourSalary;

    EmployeeType(int maxYear, EmployeeType nextLevel, double hourSalary) {
        MAX_YEAR = maxYear;
        this.nextLevel = nextLevel;
        this.hourSalary = hourSalary;
    }

    public EmployeeType getNextLevel() {
        return nextLevel;
    }

    public double getHourSalary() {
        return hourSalary;
    }

    public static EmployeeType valueOf(int year) {
        if(SENIOR.MAX_YEAR <= year) {
            return SENIOR;
        } else if(INTERMEDIATE.MAX_YEAR <= year) {
            return INTERMEDIATE;
        } else
            return JUNIOR;
    }
}
