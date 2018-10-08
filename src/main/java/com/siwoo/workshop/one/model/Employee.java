package com.siwoo.workshop.one.model;

import com.siwoo.workshop.one.EmployeeProgram;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sm123tt@gmail.com on 2018-10-08
 * Project : workshop1
 * Github : http://github.com/Siwoo-Kim
 */

public interface Employee {

    void addHour(DayOfWeek dayOfWeek, double hours);

    double fetchSalary();

    double viciousSalary();

    //Not supported
    void plusHour(DayOfWeek dayOfWeek, double hours);

    EmployeeType getType();
}
