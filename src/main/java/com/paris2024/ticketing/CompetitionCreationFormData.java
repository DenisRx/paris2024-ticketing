package com.paris2024.ticketing;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionCreationFormData {

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date startDateTime;
	private Long stageId;
	private Long disciplineId1;
	private Long disciplineId2;
	private int olympicNumber1;
	private int olympicNumber2;
	private BigDecimal ticketPrice;
	private int seatNumber;
}
