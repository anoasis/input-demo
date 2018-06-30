package com.example.inputdemo;

import com.example.inputdemo.util.InputMapper;
import com.example.inputdemo.model.Person;
import com.example.inputdemo.model.PersonInput;
import com.example.inputdemo.model.input.CurrencyCode;
import com.example.inputdemo.model.input.UnitCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AnnotationMain {
    public static void main(String[] args) throws JsonProcessingException {

        Person p = new Person();
        //default value
        p.setFirstName("first");
        p.setLastName("last");
        //DOB will be ignored since no annotation was given
        p.setAddress("address");

        //wire the unit code based on the given "unitCode" attribute
        p.setSalary(new Double(1000.0));
        p.setSalaryCurrency(CurrencyCode.USD);
        p.setWorkDuration(40);
        p.setWorkUnitCode(UnitCode.HUR);

        //Autowire UnitCode based on field name inference (eg. WorkHours, VacationDays, years)
        //p.setWorkHours(80);

        PersonInput pr = new PersonInput();
        pr.setMethodId("person");
        pr.setInputs(InputMapper.getInputs(p));

        ObjectMapper om = new ObjectMapper();
        System.out.println("Original: "+om.writeValueAsString(p));
        System.out.println("Transform: "+om.writeValueAsString(pr));
    }
}
