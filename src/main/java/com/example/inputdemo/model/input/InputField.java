package com.example.inputdemo.model.input;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface InputField {

    @AliasFor("variableId")
    String id() default "";

    @AliasFor("type")
    InputType type() default InputType.String;

    @AliasFor("unitCode")
    String[] unitCode() default {};

    @AliasFor("required")
    boolean required() default false;

    @AliasFor("validation")
    String validation() default "";
}
