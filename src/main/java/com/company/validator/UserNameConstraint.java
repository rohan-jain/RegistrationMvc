package com.company.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import  java.lang.annotation.ElementType;
import  java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

@Deprecated
@Documented
@Constraint(validatedBy = UserNameValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UserNameConstraint {
    String message() default "Invalid Username";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}