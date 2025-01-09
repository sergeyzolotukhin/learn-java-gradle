package ua.in.sz.hibernate.identifiers.assigned.entities;

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

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "DERIVATION")
public class CompositeAssignedDerivation {

	@Id
	@Column(name = "ID")
	private Long id;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_generator_02")
	@SequenceGenerator(name = "employee_generator_02", sequenceName = "employee_seq_02", allocationSize = 1)
	@Column(name = "SUB_ID")
	private Long subId;

	@Column(name = "NAME")
	@EqualsAndHashCode.Include
	private String name;
}
