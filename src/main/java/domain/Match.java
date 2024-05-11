package domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "matches")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
@ToString
public class Match implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Sport sport;

	private Date startDateTime;

	@ManyToOne
	private Stage stage;

	private int olympicNumber1;

	private int olympicNumber2;

	@Column(precision = 10, scale = 2)
	private BigDecimal ticketPrice;

	private int seatsNumber;

	private int remainingSeats;

	@ManyToMany
	private Set<Discipline> disciplines = new HashSet<>();

	public void addDiscipline(Discipline discipline) {
		disciplines.add(discipline);
	}

	public void removeDiscipline(Discipline discipline) {
		disciplines.remove(discipline);
	}

	public Set<Discipline> getDisciplines() {
		return Collections.unmodifiableSet(disciplines);
	}
}
