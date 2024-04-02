package ua.in.sz.hibernate.multiple.sessions.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;


import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "DERIVATION")
public class Derivation {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	@Column(name = "NAME")
	@EqualsAndHashCode.Include
	private String name;

	@OneToMany(mappedBy = "derivation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Builder.Default
	@Setter(AccessLevel.NONE)
	protected Set<Attribute> attributes = new HashSet<>();

	public void add(Attribute attribute) {
		attribute.setDerivation(this);
	}

	public void remove(Attribute attribute) {
		attribute.setDerivation(null);
	}
}
