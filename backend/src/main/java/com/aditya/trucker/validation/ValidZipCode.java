package com.aditya.trucker.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

/**
 * Validates that the annotated string is a valid US ZIP code.
 * The ZIP code must be in the format 12345 or 12345-6789.
 */
@Documented
@Constraint(validatedBy = ZipCodeValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidZipCode {
    String message() default "Invalid ZIP code";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
