package com.example.inputdemo.model.input;

import java.math.BigDecimal;

public class InputQuantity extends InputValue {

    private int quantity;
    private UnitCode unitCode;

    public int getQuantity() {

        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public UnitCode getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(UnitCode code) {
        this.unitCode = code;
    }
}
