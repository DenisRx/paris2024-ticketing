package domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonPropertyOrder({ "competitionId", "availablePlaces" })
public class CompetitionPlaces implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("competitionId")
	private long competitionId;

	@JsonProperty("availablePlaces")
	private int availablePlaces;
}
