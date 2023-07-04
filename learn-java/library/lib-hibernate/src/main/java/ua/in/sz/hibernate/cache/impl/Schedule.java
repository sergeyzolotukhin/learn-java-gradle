package ua.in.sz.hibernate.cache.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.Synchronize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
public class Schedule {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	@Column(name = "NAME")
	@EqualsAndHashCode.Include
	private String name;
	@ManyToOne
	private Workspace workspace;

	public void setWorkspace(Workspace workspace) {
		if (this.workspace != null) {
			this.workspace.schedules.remove(this);
		}

		this.workspace = workspace;

		if (this.workspace != null) {
			this.workspace.schedules.add(this);
		}
	}
}
