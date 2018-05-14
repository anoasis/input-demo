package com.example.inputdemo.controller;

import com.example.inputdemo.avro.AvroWriter;
import com.example.inputdemo.avro.GeneralPerson;
import com.example.inputdemo.model.Person;
import com.example.inputdemo.model.PersonInput;
import com.example.inputdemo.model.Person_v2;
import com.example.inputdemo.model.input.Input;
import com.example.inputdemo.util.InputMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path="/person")
public class AvroController {

    @GetMapping(path="/v1/convert/{version}")
    public @ResponseBody String covertVersion(@PathVariable("version") String version, @RequestBody Person p) throws IOException {
        GeneralPerson gp = GeneralPerson.generalize_v1(p);
        gp.serialize_v2(gp);
        return AvroWriter.json_v2(gp);
    }

    @GetMapping(path="/v1/convert/{version}/input")
    public @ResponseBody PersonInput covertVersionAndSendInput(@PathVariable("version") String version, @RequestBody Person p) throws IOException {
        GeneralPerson gp = GeneralPerson.generalize_v1(p);
        gp.serialize_v2(gp);
        String json = AvroWriter.json_v2(gp);
        ObjectMapper om = new ObjectMapper();
        Person_v2 p2 = om.readValue(json, Person_v2.class);
        PersonInput pi = new PersonInput();
        pi.setId(1);
        List<Input> inputs = InputMapper.getInputs(p2);
        pi.setInputs(inputs);
        return pi;
    }

    @GetMapping(path="/v2/convert/{version}")
    public @ResponseBody String covertVersionFromV2(@PathVariable("version") String version, @RequestBody Person_v2 p) throws IOException {
        GeneralPerson gp = GeneralPerson.generalize_v2(p);
        gp.serialize_v1(gp);
        return AvroWriter.json_v1(gp);
    }

    @GetMapping(path="/v2/convert/{version}/input")
    public @ResponseBody PersonInput covertVersionAndSendInputFromV2(@PathVariable("version") String version, @RequestBody Person_v2 p) throws IOException {
        GeneralPerson gp = GeneralPerson.generalize_v2(p);
        gp.serialize_v1(gp);
        String json = AvroWriter.json_v1(gp);
        ObjectMapper om = new ObjectMapper();
        Person p2 = om.readValue(json, Person.class);
        PersonInput pi = new PersonInput();
        pi.setId(1);
        List<Input> inputs = InputMapper.getInputs(p2);
        pi.setInputs(inputs);
        return pi;
    }
}
