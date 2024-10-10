package ua.in.sz.hibernate.identifiers.generated.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
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
public class CompositeDerivation {
	/*
	 * We are using a sequences generator with a pooled optimization by default
	 */

	/*
	 * if we set allocationSize <= 1 then we are  using a NOOP optimizer
	 * otherwise we are using a POOLED optimizer
	 *
	 * org.hibernate.id.enhanced.SequenceStyleGenerator.determineOptimizationStrategy
	 */

	@Id
//	@GeneratedValue
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_generator_01")
	@SequenceGenerator(name = "employee_generator_01", sequenceName = "employee_seq_01", allocationSize = 1)
	@Column(name = "ID")
	private Long id;

	@Id
//	@GeneratedValue
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_generator_02")
	@SequenceGenerator(name = "employee_generator_02", sequenceName = "employee_seq_02", allocationSize = 1)
	@Column(name = "SUB_ID")
	private Long subId;

	@Column(name = "NAME")
	@EqualsAndHashCode.Include
	private String name;
}
