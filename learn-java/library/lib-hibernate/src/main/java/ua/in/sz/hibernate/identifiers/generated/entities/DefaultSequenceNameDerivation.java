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

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "DERIVATION")
public class DefaultSequenceNameDerivation {
	@Id
	@GeneratedValue
//	@SequenceGenerator(allocationSize = 25)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	@EqualsAndHashCode.Include
	private String name;
}
