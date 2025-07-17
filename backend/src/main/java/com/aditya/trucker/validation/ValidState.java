package com.aditya.trucker.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

/**
 * Validates that the annotated string is a valid US state abbreviation.
 */
@Documented
@Constraint(validatedBy = StateValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidState {
    String message() default "Invalid US state abbreviation";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
