package com.revature.assignforce.validators;


import java.lang.annotation.ElementType;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author Hayden
 */
@Repeatable(IsValidInterval.List.class)
@Target({TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = IsValidIntervalValidator.class)
public @interface IsValidInterval
{
    // list for repeated annotations
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE})
    @interface List 
    {
       IsValidInterval[] value();
    }
    
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
    // identifiers of start and end of the interval
    String startInterval() default "startInterval";
    String endInterval() default "endInterval";
    
    // wether the end of the interval is inclusive or exclusive
    boolean inclusive() default false;
    
    // whether to return true or false if one of the bounds is null
    boolean ifNull() default false;
    
    // message if validation fails
    String message() default "{Interval is invalid}";   
}
