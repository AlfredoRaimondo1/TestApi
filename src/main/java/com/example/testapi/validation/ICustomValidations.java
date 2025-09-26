package com.example.testapi.validation;

import com.example.testapi.validation.impl.CustomValidationImpl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ICustomValidations {
    ICustomValidation[] value();

}
