package com.example.inputdemo.util;

import com.example.inputdemo.model.Person;
import com.example.inputdemo.model.input.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class InputMapper {

    public static List<Input> getInputs(Object obj) {
        try {
            Class<?> objectClass = requireNonNull(obj).getClass();
            Map<String, InputValue> inputElements = new HashMap<>();

            for (Field field: objectClass.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(InputField.class)) {
                    String variableId = (field.getAnnotation(InputField.class).id().isEmpty()) ? field.getName() : field.getAnnotation(InputField.class).id();
                    InputType type = field.getAnnotation(InputField.class).type();

                    switch(type){

                        case String:
                            InputString str = new InputString();
                            str.setInputValue((String) field.get(obj));
                            inputElements.put(variableId, str);
                            break;

                        case Number:
                            InputNumber number = new InputNumber();
                            number.setNumber((int) field.get(obj));
                            inputElements.put(variableId, number);
                            break;

                        case Quantity:
                            InputQuantity qty = new InputQuantity();
                            qty.setQuantity((int) field.get(obj));
                            String[] codes = field.getAnnotation(InputField.class).unitCode();
                            for (String code : codes){
                                Field f = objectClass.getDeclaredField(code);
                                f.setAccessible(true);
                                qty.setUnitCode((UnitCode) f.get(obj));
                            }
                            inputElements.put(variableId, qty);
                            break;

                        case QuantityAutowire:
                            InputQuantity qtya = new InputQuantity();
                            qtya.setQuantity((int) field.get(obj));
                            if(field.getName().compareToIgnoreCase("hour")>0) qtya.setUnitCode(UnitCode.HUR);
                            inputElements.put(variableId, qtya);
                            break;

                        case Amount:
                            InputAmount amount = new InputAmount();
                            amount.setAmount((Double) field.get(obj));
                            String[] currencyCodes = field.getAnnotation(InputField.class).unitCode();
                            for (String code : currencyCodes){
                                Field f = objectClass.getDeclaredField(code);
                                f.setAccessible(true);
                                amount.setCurrencyCode(((CurrencyCode) f.get(obj)));
                            }
                            inputElements.put(variableId, amount);
                            break;
                    }

                }
            }
            return inputElements.keySet().stream().map(id -> new Input(id,inputElements.get(id))).collect(Collectors.toList());
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }


}
