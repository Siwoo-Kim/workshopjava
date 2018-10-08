package com.siwoo.workshop.one.model;

import java.time.DayOfWeek;
import java.util.HashMap;

/**
 * Created by sm123tt@gmail.com on 2018-10-08
 * Project : workshop1
 * Github : http://github.com/Siwoo-Kim
 */

public interface WeekSalary {
    int WEEK_LIMIT_OVERTIME = 44;
    double INSURANCE_DEDUCTION = 7.5d;
    double OVERTIME_PAY_RATE = 1.5;

    void addSalary(DayOfWeek dayOfWeek, double hours);

    double totalHours();

    Double getWeekSalary();

    static double deductInsurance(double salary) {
        return salary - INSURANCE_DEDUCTION;
    }
}
