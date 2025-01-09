package ua.in.sz.hibernate.identifiers.optimizer.pooled.entities;

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
@Table(name = "POOLED_DERIVATION")
public class PooledDerivation {
	@Id
	@GeneratedValue(generator = "sequence-generator-01")
	@GenericGenerator(
			name = "sequence-generator-01",
			strategy = "sequence",
			parameters = {
					@Parameter(name = "sequence_name",  value = "user_sequence_01"),
					@Parameter(name = "initial_value",  value = "1"),
					@Parameter(name = "increment_size",  value = "10"),
					@Parameter(name = "optimizer", value = "pooled")
			}
	)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	@EqualsAndHashCode.Include
	private String name;
}
