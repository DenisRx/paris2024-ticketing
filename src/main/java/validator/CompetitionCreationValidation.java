package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import domain.CompetitionCreationFormData;

public class CompetitionCreationValidation implements Validator {

	@Override
	public boolean supports(Class<?> classType) {
		return CompetitionCreationFormData.class.isAssignableFrom(classType);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CompetitionCreationFormData formData = (CompetitionCreationFormData) target;

		Long disciplineId1 = formData.getDisciplineId1();
		Long disciplineId2 = formData.getDisciplineId2();
		int olympicNumber1 = formData.getOlympicNumber1();
		int olympicNumber2 = formData.getOlympicNumber2();

		if (disciplineId1 != null && disciplineId2 != null && disciplineId1 == disciplineId2) {
			errors.rejectValue("disciplineId2", "newCompetition.validation.disciplines.twiceSelected.message");
		}

		if (olympicNumber2 < olympicNumber1 - 1000 || olympicNumber2 > olympicNumber1 + 1000) {
			errors.rejectValue("olympicNumber2", "newCompetition.validation.olympicNumber2.outOfRange.message");
		}
	}

}
