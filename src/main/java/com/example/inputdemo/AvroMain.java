package com.example.inputdemo;

import com.example.inputdemo.avro.AvroReader;
import com.example.inputdemo.avro.AvroWriter;
import com.example.inputdemo.avro.GeneralPerson;
import com.example.inputdemo.model.Person;
import com.example.inputdemo.model.input.CurrencyCode;
import com.example.inputdemo.model.input.UnitCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class AvroMain {

    public static void main(String[] args) throws JsonProcessingException {
        Person p1 = new Person("first", "last", "address", new Double(100000.0), CurrencyCode.USD, 40, UnitCode.HUR, 0);
        Person p2 = new Person("first2", "last", "address", new Double(100000.0), CurrencyCode.USD, 40, UnitCode.HUR, 0);
        Person p3 = new Person("first3", "last", "address", new Double(100000.0), CurrencyCode.USD, 40, UnitCode.HUR, 0);
        Person[] people1 = new Person[] {p1,p2,p3};

        File af = new File("test.avro");
        File af2 = new File("test2.avro");
        File jf = new File("test.json");
        File jf2 = new File("test2.json");

        try {
            AvroWriter.avroWrite_v1(af,people1);

            GeneralPerson[] gp = new GeneralPerson[people1.length];
            for (int i = 0; i < people1.length; i++) {
                gp[i] = GeneralPerson.generalize_v1(people1[i]);
            }
            AvroWriter.avroWrite_v2(af2,gp);

            AvroReader.readAvro_v1(af);
            AvroReader.readAvro_v2(af2);

            GeneralPerson[] generalPeople1 = new GeneralPerson[people1.length];
            int i=0;
            for(Person p : people1){
                generalPeople1[i++] = GeneralPerson.generalize_v1(p);
            }
            AvroWriter.jsonWrite_v1(jf, generalPeople1);
            AvroWriter.jsonWrite_v2(jf2, generalPeople1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObjectMapper om = new ObjectMapper();
    }
}
