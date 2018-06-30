package com.example.inputdemo.model;

import com.example.inputdemo.model.input.Input;

import java.util.List;

public class PersonInput {

    String methodId;

    List<Input> inputs;

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String id) {
        this.methodId = id;
    }

    public List<Input> getInputs() {
        return inputs;
    }

    public void setInputs(List<Input> inputs) {
        this.inputs = inputs;
    }

}
