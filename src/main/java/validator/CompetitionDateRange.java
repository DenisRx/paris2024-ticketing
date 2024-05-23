package validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CompetitionDateRangeValidator.class)
public @interface CompetitionDateRange {

	String message() default "{newCompetition.validation.startDateTime.outOfRange.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
