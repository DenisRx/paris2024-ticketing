package validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OlympicNumber1FormatValidator.class)
public @interface OlympicNumber1Format {

	String message() default "{newCompetition.validation.olympicNumber1.format.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
