package com.siwoo.workshop.one;

import com.siwoo.workshop.one.model.Employee;
import com.siwoo.workshop.one.model.EmployeeImpl;
import com.siwoo.workshop.one.model.EmployeeType;
import com.siwoo.workshop.one.model.WeekSalary;
import org.junit.Test;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by sm123tt@gmail.com on 2018-10-08
 * Project : workshop1
 * Github : http://github.com/Siwoo-Kim
 */

public class EmployeeProgram {

    //Weekly Payment system.
    //Hourly paid employees
    //https://www.ontario.ca/document/your-guide-employment-standards-act-0/overtime-pay
    //Any hours worked over 44 in a week are overtime hours.

    @Test
    public void test() {
        Employee employee = new EmployeeImpl(11, null, false);
        assertEquals(employee.getType(), EmployeeType.SENIOR);
        employee.addHour(DayOfWeek.MONDAY, 3);
        assertEquals(employee.fetchSalary(), 60, 0);
        employee.addHour(DayOfWeek.TUESDAY, 6);
        assertEquals(employee.fetchSalary(), 180, 0);
        //Override for same day.
        employee.addHour(DayOfWeek.TUESDAY, 9);
        assertEquals(employee.fetchSalary(), 240, 0);
        //=12
        employee.addHour(DayOfWeek.WEDNESDAY, 9);
        //=21
        employee.addHour(DayOfWeek.THURSDAY, 9);
        //=30
        employee.addHour(DayOfWeek.FRIDAY, 9);
        //=39
        employee.addHour(DayOfWeek.SATURDAY, 6);
        //=45
        //44 for Regular Hour payment / 1 hours for Over payment
        //System.out.println(employee.weekSalary.totalHours());
        assertEquals(employee.fetchSalary(), (44. *  EmployeeType.SENIOR.getHourSalary()) + (1.5 * EmployeeType.SENIOR.getHourSalary()) , 0);
        employee.addHour(DayOfWeek.SUNDAY, 12);
        //=57
        //44 for Regular Hour payment / 13 hours for Over payment
        assertEquals(employee.fetchSalary(), (44. *  EmployeeType.SENIOR.getHourSalary()) + ((1.5 * EmployeeType.SENIOR.getHourSalary()) * 13) , 0);

        //System.out.println(employee.fetchSalary());
        //System.out.println(employee);

        employee = new EmployeeImpl(5, null, true);
        assertEquals(employee.getType(), EmployeeType.INTERMEDIATE);
        //System.out.println(employee);

        employee = new EmployeeImpl(2, null, false);
        assertEquals(employee.getType(), EmployeeType.JUNIOR);
        //System.out.println(employee);

        employee = new EmployeeImpl(3, null, false);
        //System.out.println(employee);
        //assertEquals(employee.type, Employee.TYPE.INTERMEDIATE);


//        Hourly paid employees
//                Example
//        Ravi’s regular pay is $17.00 an hour. His overtime rate (1½ X regular hourly pay) is $25.50 an hour. This week Ravi worked the following hours:
//
//        Sunday: 0 hours
//        Monday: 8 hours
//        Tuesday: 12 hours
//        Wednesday: 9 hours
//        Thursday: 8 hours
//        Friday: 8 hours
//        Saturday: 8 hours
//        Total: 53 hours
//        Any hours worked over 44 in a week are overtime hours. Ravi worked nine hours of overtime (53 − 44 = 9).
//
//        Ravi’s pay for the week is calculated as follows:
//
//        Regular pay: 44 X $17.00 = $748.00
//        Overtime pay: 9 X $25.50 = $229.50
//        Total pay: $748.00 + $229.50 = $977.50
//        Result: Ravi is entitled to total pay of $977.50.

        employee = new EmployeeImpl(0, null, true);
        assertEquals(employee.getType(), EmployeeType.JUNIOR);
        employee.addHour(DayOfWeek.SUNDAY, 0);
        employee.addHour(DayOfWeek.MONDAY, 8);
        employee.addHour(DayOfWeek.TUESDAY, 12);
        employee.addHour(DayOfWeek.WEDNESDAY, 9);
        employee.addHour(DayOfWeek.THURSDAY, 8);
        employee.addHour(DayOfWeek.FRIDAY, 8);
        employee.addHour(DayOfWeek.SATURDAY, 8);
        //assertEquals(employee.weekSalary.totalHours(), 53.0, 0);
        assertEquals(employee.fetchSalary(), 977.50, 0);
        //Insurance deduction
        //assertEquals(employee.viciousSalary(), 977.50 - Employee.WeekSalary.INSURANCE_DEDUCTION, 0);
        assertEquals(employee.viciousSalary(), 977.50 - WeekSalary.INSURANCE_DEDUCTION, 0);

    }
//
//    public static class Employee {
//        private enum TYPE {
//            SENIOR(10, null, 20.), INTERMEDIATE(3, SENIOR, 19.), JUNIOR(0, INTERMEDIATE, 17.);
//            private final int MAX_YEAR;
//            private TYPE nextLevel;
//            private final double hourSalary;
//
//            TYPE(int maxYear, TYPE nextLevel, double hourSalary) {
//                MAX_YEAR = maxYear;
//                this.nextLevel = nextLevel;
//                this.hourSalary = hourSalary;
//            }
//
//            public static TYPE valueOf(int year) {
//                if(SENIOR.MAX_YEAR <= year) {
//                    return SENIOR;
//                } else if(INTERMEDIATE.MAX_YEAR <= year) {
//                    return INTERMEDIATE;
//                } else
//                    return JUNIOR;
//            }
//        }
//
//        private int expYear;
//        private final String name;
//        private TYPE type;
//        private WeekSalary weekSalary;
//        private boolean insurance;
//
//        public Employee(int expYear, String name, boolean insurance) {
//            this.expYear = expYear;
//            this.name = name;
//            this.type = TYPE.valueOf(expYear);
//            this.weekSalary = new WeekSalary(type.hourSalary);
//            this.insurance = insurance;
//        }
//
//        public void addHour(DayOfWeek dayOfWeek, double hours) {
//            weekSalary.addSalary(dayOfWeek, hours);
//        }
//
//        public double fetchSalary() {
//            return this.weekSalary.getWeekSalary();
//        }
//
//        public double viciousSalary() {
//            if(insurance) {
//                return WeekSalary.deductInsurance(this.weekSalary.getWeekSalary());
//            } else {
//                return fetchSalary();
//            }
//        }
//
//        public void plusHour(DayOfWeek dayOfWeek, double hours) {
//            //Maybe next time..
//            throw new UnsupportedOperationException();
//        }
//
//        private static class WeekSalary {
//            private static final int WEEK_LIMIT_OVERTIME = 44;
//            public static final double INSURANCE_DEDUCTION = 7.5d;
//            private static final double OVERTIME_PAY_RATE = 1.5;
//
//            private Map<DayOfWeek, Double> workDayOfWeek;
//            private boolean insurance;
//            private double salary;
//
//            public WeekSalary(double salary) {
//                this.salary = salary;
//                this.workDayOfWeek = new HashMap();
//            }
//
//            public void addSalary(DayOfWeek dayOfWeek, double hours) {
//                workDayOfWeek.put(dayOfWeek, hours);
//            }
//
//            public double totalHours() {
//                double sum = 0;
//                for(Double hours : workDayOfWeek.values()) {
//                    if(hours != null) {
//                        sum += hours;
//                    }
//                }
//                return sum;
//            }
//
//            public double getOvertimePayment() {
//                double overTimes = totalHours() - WEEK_LIMIT_OVERTIME;
//                double sum = 44. * salary;
//                sum += (overTimes * OVERTIME_PAY_RATE) * salary;
//                return sum;
//            }
//
//            public double getRegularPayment() {
//                return salary * totalHours();
//            }
//
//            public Double getWeekSalary() {
//                if(totalHours() >= WEEK_LIMIT_OVERTIME) {
//                    return getOvertimePayment();
//                } else {
//                    return getRegularPayment();
//                }
//            }
//
//            public static double deductInsurance(double salary) {
//                return salary - INSURANCE_DEDUCTION;
//            }
//
//            @Override
//            public String toString() {
//                return "WeekSalary{" +
//                        "workDayOfWeek=" + workDayOfWeek +
//                        ", insurance=" + insurance +
//                        ", salary=" + salary +
//                        '}';
//            }
//        }
//
//        @Override
//        public String toString() {
//            return "Employee{" +
//                    "expYear=" + expYear +
//                    ", name='" + name + '\'' +
//                    ", type=" + type +
//                    '}';
//        }
//    }


}
