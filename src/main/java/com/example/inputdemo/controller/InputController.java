package com.example.inputdemo.controller;

import com.example.inputdemo.model.Person;
import com.example.inputdemo.model.PersonInput;
import com.example.inputdemo.util.InputMapper;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/person")
public class InputController {

    @GetMapping(path="/single")
    public @ResponseBody
    PersonInput covertToReq(@RequestBody Person p){
        PersonInput pr = new PersonInput();
        pr.setId(1);
        pr.setInputs(InputMapper.getInputs(p));
        return pr;
    }

    @GetMapping(path="/multiple")
    public @ResponseBody List<PersonInput> covertToReq(@RequestBody List<Person> list){
        List<PersonInput> prl = new ArrayList<PersonInput>();
        int index = 1;
        for(Person p: list){
            PersonInput pr = new PersonInput();
            pr.setId(index++);
            pr.setInputs(InputMapper.getInputs(p));
            prl.add(pr);
        }
        return prl;
    }
}
