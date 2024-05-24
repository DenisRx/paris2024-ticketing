package validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CompetitionDateRangeValidator implements ConstraintValidator<CompetitionDateRange, Date> {

	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

	@Override
	public void initialize(CompetitionDateRange constraintAnnotation) {
	}

	@Override
	public boolean isValid(Date date, ConstraintValidatorContext context) {
		try {
			Date startDate = dateFormat.parse("2024-07-26T08:00");
			Date endDate = dateFormat.parse("2024-08-11T23:59");

			if (date == null || date.compareTo(startDate) < 0 || date.compareTo(endDate) > 0) {
				return false;
			}

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);

			return calendar.get(Calendar.HOUR_OF_DAY) >= 8;
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
	}
}
