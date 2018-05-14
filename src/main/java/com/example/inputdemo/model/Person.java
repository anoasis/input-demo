package com.example.inputdemo.model;

import com.example.inputdemo.model.input.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

public class Person {

    @InputField
    private String firstName;

    @InputField
    private String lastName;

    private String address;

    @InputField(type=InputType.Amount, unitCode = "salaryCurrency")
    private double salary;
    @JsonProperty(required = true)
    private CurrencyCode salaryCurrency;

    @InputField(id="workHours", type=InputType.Quantity, unitCode = "workUnitCode")
    private int workDuration;
    private UnitCode workUnitCode;

    @InputField(type=InputType.Number)
    private int numberOfdependents;

    public Person() { }
    public Person(String firstName, String lastName, String address, double salary, CurrencyCode salaryCurrency, int workDuration, UnitCode workUnitCode, int numberOfdependents) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.salary = salary;
        this.salaryCurrency = salaryCurrency;
        this.workDuration = workDuration;
        this.workUnitCode = workUnitCode;
        this.numberOfdependents = numberOfdependents;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getWorkDuration() {
        return workDuration;
    }

    public void setWorkDuration(int workDuration) {
        this.workDuration = workDuration;
    }

    public UnitCode getWorkUnitCode() {
        return workUnitCode;
    }

    public void setWorkUnitCode(UnitCode workUnitCode) {
        this.workUnitCode = workUnitCode;
    }

    public int getNumberOfdependents() {
        return numberOfdependents;
    }

    public void setNumberOfdependents(int numberOfdependents) {
        this.numberOfdependents = numberOfdependents;
    }

    public CurrencyCode getSalaryCurrency() {
        return salaryCurrency;
    }

    public void setSalaryCurrency(CurrencyCode salaryCurrency) {
        this.salaryCurrency = salaryCurrency;
    }

}
