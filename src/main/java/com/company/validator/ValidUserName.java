package com.company.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import  java.lang.annotation.ElementType;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target( { TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = { ValidUserNameValidator.class })
@Documented
public @interface ValidUserName {
	
    String message() default "Invalid Username";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}