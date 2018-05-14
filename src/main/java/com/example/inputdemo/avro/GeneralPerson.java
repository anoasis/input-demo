package com.example.inputdemo.avro;

import com.example.inputdemo.model.Person;
import com.example.inputdemo.model.Person_v2;
import com.example.inputdemo.model.input.CurrencyCode;
import com.example.inputdemo.model.input.InputField;
import com.example.inputdemo.model.input.UnitCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.avro.LogicalTypes;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.io.Encoder;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.util.Utf8;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GeneralPerson {
    public static Schema V1;
    public static Schema V2;

    static {
        try {
            Schema schema = LogicalTypes.date().addToSchema(Schema.create(Schema.Type.INT));
            V1 = schema.parse(Person.class.getResourceAsStream("/Person-v1.avsc"));
            V2 = schema.parse(Person.class.getResourceAsStream("/Person-v2.avsc"));
        }
        catch (IOException e)
        {
            System.out.println("Couldn't load a schema: "+e.getMessage());
        }
    }

    private String firstName;
    private String lastName;
    private String homeAddress;
    private String workAddress;
    private double salary;
    private String salaryCurrency;
    private int workDuration;
    private String workUnitCode;
    private int workHours;
    private int numberOfdependents;

    public GeneralPerson(String firstName, String lastName, String homeAddress, String workAddress, double salary, CurrencyCode salaryCurrency, int workDuration, UnitCode workUnitCode, int workHours, int numberOfdependents) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.homeAddress = homeAddress;
        this.workAddress = workAddress;
        this.salary = salary;
        if(salaryCurrency!=null) this.salaryCurrency = salaryCurrency.name();
        this.workDuration = workDuration;
        if(workUnitCode!=null) this.workUnitCode = workUnitCode.name();
        this.workHours = workHours;
        this.numberOfdependents = numberOfdependents;
    }

    public static GeneralPerson generalize_v1(Person p) {
        return new GeneralPerson(p.getFirstName(),p.getLastName(),p.getAddress(), "", p.getSalary(), p.getSalaryCurrency(), p.getWorkDuration(), p.getWorkUnitCode(), 0, p.getNumberOfdependents());
    }

    public static GeneralPerson generalize_v2(Person_v2 p) {
        return new GeneralPerson(p.getFirstName(),p.getLastName(), p.getHomeAddress(), p.getWorkAddress(), p.getSalary(), p.getSalaryCurrency(), 0, null, p.getWorkHours(), p.getNumOfdependents());
    }

    public GenericData.Record serialize_v1(GeneralPerson gp) {
        GenericData.Record record = new GenericData.Record(V1);

        record.put("firstName", gp.firstName);
        record.put("lastName", gp.lastName);
        record.put("address", gp.homeAddress);
        record.put("salary", gp.salary);
        record.put("salaryCurrency", gp.salaryCurrency);
        if(gp.workDuration==0 && gp.workHours>0) record.put("workDuration", gp.workHours);
        else record.put("workDuration", gp.workDuration);
        if(gp.workUnitCode==null) record.put("workUnitCode", UnitCode.HUR.name());
        else record.put("workUnitCode", gp.workUnitCode);
        record.put("numberOfdependents", gp.numberOfdependents);

        return record;
    }

    public GenericData.Record serialize_v2(GeneralPerson gp) {
        GenericData.Record record = new GenericData.Record(V2);

        record.put("firstName", gp.firstName);
        record.put("lastName", gp.lastName);
        record.put("homeAddress", gp.homeAddress);
        record.put("workAddress", gp.workAddress);

        record.put("salary", gp.salary);
        record.put("salaryCurrency", gp.salaryCurrency);
        if(gp.workHours==0 && gp.workDuration>0) record.put("workHours", gp.workDuration);
        else record.put("workHours", gp.workHours);
        record.put("numOfdependents", gp.numberOfdependents);

        return record;
    }

}
