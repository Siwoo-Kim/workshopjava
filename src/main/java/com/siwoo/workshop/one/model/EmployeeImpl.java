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

public class EmployeeImpl implements Employee {

    private int expYear;
    private final String name;
    private EmployeeType type;
    private WeekSalary weekSalary;
    private boolean insurance;

    public EmployeeImpl(int expYear, String name, boolean insurance) {
        this.expYear = expYear;
        this.name = name;
        this.type = EmployeeType.valueOf(expYear);
        this.weekSalary = new WeekSalaryImpl(type.getHourSalary());
        this.insurance = insurance;
    }

    @Override
    public EmployeeType getType() {
        return type;
    }

    public void addHour(DayOfWeek dayOfWeek, double hours) {
        weekSalary.addSalary(dayOfWeek, hours);
    }

    public double fetchSalary() {
        return this.weekSalary.getWeekSalary();
    }

    public double viciousSalary() {
        if(insurance) {
            return WeekSalary.deductInsurance(this.weekSalary.getWeekSalary());
        } else {
            return fetchSalary();
        }
    }

    public void plusHour(DayOfWeek dayOfWeek, double hours) {
        //Maybe next time..
        throw new UnsupportedOperationException();
    }

    private static class WeekSalaryImpl implements WeekSalary {
        private Map<DayOfWeek, Double> workDayOfWeek;
        private double salary;

        public WeekSalaryImpl(double salary) {
            this.salary = salary;
            this.workDayOfWeek = new HashMap();
        }

        public void addSalary(DayOfWeek dayOfWeek, double hours) {
            workDayOfWeek.put(dayOfWeek, hours);
        }

        public double totalHours() {
            double sum = 0;
            for(Double hours : workDayOfWeek.values()) {
                if(hours != null) {
                    sum += hours;
                }
            }
            return sum;
        }

        private double getOvertimePayment() {
            double overTimes = totalHours() - WEEK_LIMIT_OVERTIME;
            double sum = 44. * salary;
            sum += (overTimes * OVERTIME_PAY_RATE) * salary;
            return sum;
        }

        private double getRegularPayment() {
            return salary * totalHours();
        }

        public Double getWeekSalary() {
            if(totalHours() >= WEEK_LIMIT_OVERTIME) {
                return getOvertimePayment();
            } else {
                return getRegularPayment();
            }
        }

        private static double deductInsurance(double salary) {
            return salary - INSURANCE_DEDUCTION;
        }

        @Override
        public String toString() {
            return "WeekSalaryImpl{" +
                    "workDayOfWeek=" + workDayOfWeek +
                    ", salary=" + salary +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "EmployeeImpl{" +
                "expYear=" + expYear +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", weekSalary=" + weekSalary +
                ", insurance=" + insurance +
                '}';
    }
}
