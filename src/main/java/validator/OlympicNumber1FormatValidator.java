package validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OlympicNumber1FormatValidator implements ConstraintValidator<OlympicNumber1Format, Integer> {

	@Override
	public void initialize(OlympicNumber1Format constraintAnnotation) {
	}

	@Override
	public boolean isValid(Integer number, ConstraintValidatorContext context) {
		String numberStr = number.toString();
		if (numberStr.charAt(0) == '0' || numberStr.charAt(0) == numberStr.charAt(4)) {
			return false;
		}

		return true;
	}
}
