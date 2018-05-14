package com.example.inputdemo.model;

import com.example.inputdemo.model.input.CurrencyCode;
import com.example.inputdemo.model.input.InputField;
import com.example.inputdemo.model.input.InputType;

public class Person_v2 {

    @InputField
    private String firstName;

    @InputField
    private String lastName;

    @InputField
    private String homeAddress;

    @InputField
    private String workAddress;

    @InputField(type=InputType.Amount, unitCode = "salaryCurrency")
    private double salary;
    private CurrencyCode salaryCurrency;

    @InputField(type=InputType.QuantityAutowire)
    private int workHours;

    @InputField(type=InputType.Number)
    private int numOfdependents;

    public Person_v2() { }
    public Person_v2(String firstName, String lastName, String homeAddress, String workAddress, double salary, CurrencyCode salaryCurrency, int workHours, int numOfdependents) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.homeAddress = homeAddress;
        this.workAddress = workAddress;
        this.salary = salary;
        this.salaryCurrency = salaryCurrency;
        this.workHours = workHours;
        this.numOfdependents = numOfdependents;
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

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public CurrencyCode getSalaryCurrency() {
        return salaryCurrency;
    }

    public void setSalaryCurrency(CurrencyCode salaryCurrency) {
        this.salaryCurrency = salaryCurrency;
    }

    public int getWorkHours() {
        return workHours;
    }

    public void setWorkHours(int workHours) {
        this.workHours = workHours;
    }

    public int getNumOfdependents() {
        return numOfdependents;
    }

    public void setNumOfdependents(int numOfdependents) {
        this.numOfdependents = numOfdependents;
    }
}
