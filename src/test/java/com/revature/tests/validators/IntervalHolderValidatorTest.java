package com.revature.tests.validators;

import com.revature.assignforce.validators.IntervalHolder;
import com.revature.assignforce.validators.IntervalHolderValidator;

import java.lang.annotation.Annotation;
import java.time.LocalDateTime;

import javax.validation.Payload;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the IntervalHolder annotation and IntervalHolderValidator.
 * @author Hayden Fields
 */
public class IntervalHolderValidatorTest
{
    /**
     * Class for testing purposes.
     */
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
    /**
     * If start date is before end date should return true.
     */
    public void trueWhenIntervalValid()
    {
        IntervalHolderValidator test = new IntervalHolderValidator();
        test.initialize(getInstanceOfIntervalHolderAnnotation(
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
    /**
     * If end date is before start date should return false.
     */
    public void falseWhenIntervalInvalid()
    {
        IntervalHolderValidator test = new IntervalHolderValidator();
        test.initialize(getInstanceOfIntervalHolderAnnotation(
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
    /**
     * If fields are not found then that is a programming error,
     * should throw an exception so that the programmer can fix their code.
     */
    public void specifiedFieldsNotFound()
    {
        IntervalHolderValidator test = new IntervalHolderValidator();
        test.initialize(getInstanceOfIntervalHolderAnnotation(
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
    
    /**
     * Creates an annotation to test the validator.
     * @param message The error message.
     * @param startInterval Start of the interval.
     * @param endInterval End of the interval.
     * @param inclusive Whether start and end can be the same value.
     * @param ifNull Returned if either bound is null.
     * @return Returns an implemented and instantiated IntervalHolder
     *         annotation.
     */
    public IntervalHolder getInstanceOfIntervalHolderAnnotation(
                                                   final String message,
                                                   final String startInterval,
                                                   final String endInterval,
                                                   final boolean inclusive,
                                                   final boolean ifNull)
    {
        IntervalHolder annotation = new IntervalHolder()
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
                return IntervalHolder.class;
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
