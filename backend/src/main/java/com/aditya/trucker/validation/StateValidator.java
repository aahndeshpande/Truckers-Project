package com.aditya.trucker.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

/**
 * Validator for the {@link ValidState} annotation.
 * Validates that a string is a valid US state abbreviation.
 */
public class StateValidator implements ConstraintValidator<ValidState, String> {
    
    // Set of all valid US state abbreviations
    private static final Set<String> US_STATES = new HashSet<>(Arrays.asList(
        "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA",
        "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD",
        "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ",
        "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC",
        "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"
    ));
    
    @Override
    public void initialize(ValidState constraintAnnotation) {
        // No initialization needed
    }
    
    @Override
    public boolean isValid(String state, ConstraintValidatorContext context) {
        if (state == null) {
            return true; // Let @NotNull handle null values
        }
        
        // Check if the state is in our set of valid states
        return US_STATES.contains(state.toUpperCase());
    }
}
