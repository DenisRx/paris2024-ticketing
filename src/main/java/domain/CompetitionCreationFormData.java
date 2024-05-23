package domain;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import validator.CompetitionDateRange;
import validator.OlympicNumber1Format;
import validator.OlympicNumber1Unique;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionCreationFormData {

	@NotNull(message = "{newCompetition.validation.startDateTime.notNull.message}")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@CompetitionDateRange
	private Date startDateTime;

	@NotNull(message = "{newCompetition.validation.stage.notNull.message}")
	private Long stageId;

	private Long disciplineId1;
	private Long disciplineId2;

	@Range(min = 10000, max = 99998, message = "{newCompetition.validation.olympicNumber1.outOfRange.message}")
	@OlympicNumber1Format
	@OlympicNumber1Unique
	private Integer olympicNumber1;

	private Integer olympicNumber2;

	@NotNull(message = "{newCompetition.validation.ticketPrice.notNull.message}")
	@DecimalMin(value = "0.00", inclusive = false, message = "{newCompetition.validation.ticketPrice.min.message}")
	@DecimalMax(value = "150.00", inclusive = false, message = "{newCompetition.validation.ticketPrice.min.message}")
	private BigDecimal ticketPrice;

	@NotNull(message = "{newCompetition.validation.seatNumber.notNull.message}")
	@Range(min = 1, max = 49, message = "{newCompetition.validation.seatNumber.outOfRange.message}")
	private Integer seatNumber;
}
