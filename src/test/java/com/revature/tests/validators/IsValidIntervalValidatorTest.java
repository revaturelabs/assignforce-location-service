package com.revature.tests.validators;

import com.revature.assignforce.validators.IsValidInterval;
import com.revature.assignforce.validators.IsValidIntervalValidator;
import java.lang.annotation.Annotation;
import java.time.LocalDateTime;
import javax.validation.Payload;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hayden
 */
public class IsValidIntervalValidatorTest
{
    // class for testing purposes
    public static class TestEntity
    {
        private LocalDateTime startDateIdentifier;
        private LocalDateTime endDateIdentifier;
        
        public TestEntity(LocalDateTime startDateIdentifier, 
                          LocalDateTime endDateIdentifier)
        {
            this.startDateIdentifier = startDateIdentifier;
            this.endDateIdentifier = endDateIdentifier;
        }
    }
    
    @Test
    /*
    If start date is before end date should return true
    */
    public void trueWhenIntervalValid()
    {
        IsValidIntervalValidator test = new IsValidIntervalValidator();
        test.initialize(getInstanceOfIsValidIntervalAnnotation(
                                                        "irrelevant message",
                                                        "startDateIdentifier",
                                                        "endDateIdentifier",
                                                        true,
                                                        true));
        
        TestEntity testEntity = new TestEntity(
                            LocalDateTime.of(2018, 1, 1, 1, 1, 1),
                            LocalDateTime.of(2018, 1, 1, 1, 1, 1).plusDays(1));
        
        assertTrue(test.isValid(testEntity, null));
    }
    
    @Test
    /*
    If end date is before start date should return false
    */
    public void falseWhenIntervalInvalid()
    {
        IsValidIntervalValidator test = new IsValidIntervalValidator();
        test.initialize(getInstanceOfIsValidIntervalAnnotation(
                                                        "irrelevant message",
                                                        "startDateIdentifier",
                                                        "endDateIdentifier",
                                                        true,
                                                        true));
        
        TestEntity testEntity = new TestEntity(
                            LocalDateTime.of(2018, 1, 1, 1, 1, 1), 
                            LocalDateTime.of(2018, 1, 1, 1, 1, 1).minusDays(2));
        
        assertFalse(test.isValid(testEntity, null));
    }
    
    @Test
    /*
    if fields are not found then that is a programatic error
    should throw an exception so that the programmer can fix their code
    */
    public void specifiedFieldsNotFound()
    {
        IsValidIntervalValidator test = new IsValidIntervalValidator();
        test.initialize(getInstanceOfIsValidIntervalAnnotation(
                                                        "irrelevant message",
                                                        "fieldNotInClass",
                                                        "otherFieldNotInClass",
                                                        true,
                                                        true));
        LocalDateTime.of(2018, 1, 1, 1, 1, 1);
        TestEntity testEntity = new TestEntity(
                            LocalDateTime.of(2018, 1, 1, 1, 1, 1),
                            LocalDateTime.of(2018, 1, 1, 1, 1, 1).minusDays(2));
        try
        {
            test.isValid(testEntity, null);
            assertTrue(false); // should've thrown an exception
        }
        catch (IllegalArgumentException iae)
        {
            assertTrue(true);
        }
    }
    
    public IsValidInterval getInstanceOfIsValidIntervalAnnotation(
                                                   final String message,
                                                   final String startInterval,
                                                   final String endInterval,
                                                   final boolean inclusive,
                                                   final boolean ifNull)
    {
        IsValidInterval annotation = new IsValidInterval()
        {
            @Override
            public String startInterval()
            {
                return startInterval;
            }

            @Override
            public String endInterval()
            {
                return endInterval;
            }

            @Override
            public boolean inclusive()
            {
                return inclusive;
            }

            @Override
            public boolean ifNull()
            {
                return ifNull;
            }

            @Override
            public String message()
            {
                return message;
            }

            @Override
            public Class<? extends Annotation> annotationType()
            {
                return IsValidInterval.class;
            }
            
            @Override
            public Class<?>[] groups()
            {
                return new Class[] {};
            }

            @Override
            public Class<? extends Payload>[] payload()
            {
                return new Class[] {};
            }
        };
        return annotation;
    }
}
