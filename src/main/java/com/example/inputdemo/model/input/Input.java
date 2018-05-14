package com.example.inputdemo.model.input;

public class Input {
    private String inputVariableID;
    private InputValue inputValue;

    public Input(String id, InputValue value) {
        this.inputVariableID = id;
        this.inputValue = value;
    }

    public String getInputVariableID() {
        return inputVariableID;
    }

    public void setInputVariableID(String inputVariableID) {
        this.inputVariableID = inputVariableID;
    }

    public InputValue getInputValue() {
        return inputValue;
    }

    public void setInputValue(InputValue inputValue) {
        this.inputValue = inputValue;
    }
}
