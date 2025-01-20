package ua.in.sz.hibernate.second.level.cache.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "ATTRIBUTE")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Attribute {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	@Column(name = "NAME")
	@EqualsAndHashCode.Include
	private String name;
	@ManyToOne
	private Derivation derivation;

	public void setDerivation(Derivation derivation) {
		if (this.derivation != null) {
			this.derivation.attributes.remove(this);
		}

		this.derivation = derivation;

		if (this.derivation != null) {
			this.derivation.attributes.add(this);
		}
	}
}
