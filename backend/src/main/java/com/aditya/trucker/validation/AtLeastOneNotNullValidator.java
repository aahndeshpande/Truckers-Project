package com.aditya.trucker.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

/**
 * Validator implementation for the {@link AtLeastOneNotNull} annotation.
 * Checks that at least one of the specified fields is not null.
 */
public class AtLeastOneNotNullValidator implements ConstraintValidator<AtLeastOneNotNull, Object> {
    
    private String[] fieldNames;
    
    @Override
    public void initialize(AtLeastOneNotNull constraint) {
        this.fieldNames = constraint.fieldNames();
    }
    
    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        if (object == null) {
            return true; // Let @NotNull handle this
        }
        
        try {
            for (String fieldName : fieldNames) {
                Field field = object.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                Object value = field.get(object);
                
                if (value != null) {
                    return true; // At least one field is not null
                }
            }
            
            // If we get here, no fields were non-null
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                  .addConstraintViolation();
            return false;
            
        } catch (NoSuchFieldException | IllegalAccessException e) {
            // Log the error (in a real application, use a proper logger)
            System.err.println("Error during validation: " + e.getMessage());
            return false;
        }
    }
}
