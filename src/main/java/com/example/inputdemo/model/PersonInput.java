package com.example.inputdemo.model;

import com.example.inputdemo.model.input.Input;

import java.util.List;

public class PersonInput {

    int id;

    List<Input> inputs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Input> getInputs() {
        return inputs;
    }

    public void setInputs(List<Input> inputs) {
        this.inputs = inputs;
    }

}
