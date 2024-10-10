package ua.in.sz.hibernate.identifiers.generated.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "DERIVATION")
public class GenDerivation {
	@Id
	@GeneratedValue(generator = "sequence-generator-01")
	@GenericGenerator(
			name = "sequence-generator-01",
			strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
			parameters = {
					@Parameter(name = "sequence_name", value = "user_sequence_01"),
					@Parameter(name = "initial_value", value = "10"),
					@Parameter(name = "increment_size", value = "1")
			}
	)
	@Column(name = "ID")
	private Long id;

	@Id
	@GeneratedValue(generator = "sequence-generator-02")
	@GenericGenerator(
			name = "sequence-generator-02",
			strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
			parameters = {
					@Parameter(name = "sequence_name", value = "user_sequence_02"),
					@Parameter(name = "initial_value", value = "100"),
					@Parameter(name = "increment_size", value = "1")
//					@Parameter(name = "optimizer", value = "pooled")
			}
	)
	@Column(name = "SUB_ID")
	private Long subId;

	@Column(name = "NAME")
	@EqualsAndHashCode.Include
	private String name;
}
