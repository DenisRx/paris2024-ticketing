package validator;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import service.CompetitionService;

public class OlympicNumber1UniqueValidator implements ConstraintValidator<OlympicNumber1Unique, Integer> {

	@Autowired
	private CompetitionService competitionService;

	@Override
	public void initialize(OlympicNumber1Unique constraintAnnotation) {
	}

	@Override
	public boolean isValid(Integer number, ConstraintValidatorContext context) {
		return !competitionService.olympicNumber1Exists(number);
	}
}
