package ua.in.sz.jpa.persistence.unit.entities;

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
import org.hibernate.annotations.Synchronize;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "SCHEDULE")
@Synchronize("f")
public class PersistenceUnitSchedule {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	@Column(name = "NAME")
	@EqualsAndHashCode.Include
	private String name;
	@ManyToOne
	private PersistenceUnitWorkspace workspace;

	public void setWorkspace(PersistenceUnitWorkspace workspace) {
		if (this.workspace != null) {
			this.workspace.schedules.remove(this);
		}

		this.workspace = workspace;

		if (this.workspace != null) {
			this.workspace.schedules.add(this);
		}
	}
}
