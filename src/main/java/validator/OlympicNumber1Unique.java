package validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OlympicNumber1UniqueValidator.class)
public @interface OlympicNumber1Unique {

	String message() default "{newCompetition.validation.olympicNumber1.exists.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
