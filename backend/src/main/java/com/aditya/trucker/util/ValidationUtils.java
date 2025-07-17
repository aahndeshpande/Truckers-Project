package com.aditya.trucker.util;

import com.aditya.trucker.exception.ValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Utility class for common validation operations.
 */
public class ValidationUtils {
    
    private static final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = validatorFactory.getValidator();
    
    /**
     * Validates an object and throws a ValidationException if validation fails.
     *
     * @param object the object to validate
     * @param groups the validation groups to validate against
     * @throws ValidationException if validation fails
     */
    public static <T> void validate(T object, Class<?>... groups) {
        Set<ConstraintViolation<T>> violations = validator.validate(object, groups);
        if (!violations.isEmpty()) {
            Map<String, String> errors = violations.stream()
                    .collect(Collectors.toMap(
                            violation -> violation.getPropertyPath().toString(),
                            ConstraintViolation::getMessage,
                            (existing, replacement) -> existing + ", " + replacement
                    ));
            throw new ValidationException("Validation failed", errors);
        }
    }
    
    /**
     * Validates an object for update (PATCH) operations.
     * Ensures that at least one field is being updated.
     *
     * @param object the object to validate
     * @param <T> the type of the object
     * @throws ValidationException if validation fails
     */
    public static <T> void validateForUpdate(T object) {
        validate(object, UpdateGroup.class);
    }
    
    /**
     * Validates an object for create (POST) operations.
     *
     * @param object the object to validate
     * @param <T> the type of the object
     * @throws ValidationException if validation fails
     */
    public static <T> void validateForCreate(T object) {
        validate(object);
    }
}
