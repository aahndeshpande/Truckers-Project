package com.aditya.trucker.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Validator for the {@link ValidPhoneNumber} annotation.
 * Validates that a string is a valid phone number.
 */
public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {
    
    // Regular expression to match phone numbers with optional formatting
    private static final String PHONE_NUMBER_PATTERN = "^\\+?[0-9. ()-]{10,25}$";
    private static final Pattern pattern = Pattern.compile(PHONE_NUMBER_PATTERN);
    
    @Override
    public void initialize(ValidPhoneNumber constraintAnnotation) {
        // No initialization needed
    }
    
    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber == null) {
            return true; // Let @NotNull handle null values
        }
        
        // Remove all non-digit characters
        String digitsOnly = phoneNumber.replaceAll("[^0-9]", "");
        
        // Check if the resulting string has exactly 10 digits
        if (digitsOnly.length() != 10) {
            return false;
        }
        
        // Check against the pattern
        return pattern.matcher(phoneNumber).matches();
    }
}
