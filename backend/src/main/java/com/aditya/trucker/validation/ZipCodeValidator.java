package com.aditya.trucker.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Validator for the {@link ValidZipCode} annotation.
 * Validates that a string is a valid US ZIP code.
 */
public class ZipCodeValidator implements ConstraintValidator<ValidZipCode, String> {
    
    // Regular expression to match US ZIP codes (5 digits or 5+4 digits)
    private static final String ZIP_CODE_PATTERN = "^\\d{5}(-\\d{4})?$";
    private static final Pattern pattern = Pattern.compile(ZIP_CODE_PATTERN);
    
    @Override
    public void initialize(ValidZipCode constraintAnnotation) {
        // No initialization needed
    }
    
    @Override
    public boolean isValid(String zipCode, ConstraintValidatorContext context) {
        if (zipCode == null) {
            return true; // Let @NotNull handle null values
        }
        
        // Check against the pattern
        return pattern.matcher(zipCode).matches();
    }
}
