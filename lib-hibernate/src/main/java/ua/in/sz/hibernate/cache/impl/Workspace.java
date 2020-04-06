package ua.in.sz.hibernate.cache.impl;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collections;
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
@Table(name = "WORKSPACE")
public class Workspace {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	@Column(name = "NAME")
	@EqualsAndHashCode.Include
	private String name;

	@OneToMany(mappedBy = "workspace")
	@Builder.Default
	@Setter(AccessLevel.NONE)
	protected Set<Schedule> schedules = new HashSet<>();

	public void add(Schedule schedule) {
		schedule.setWorkspace(this);
	}

	public void remove(Schedule schedule) {
		schedule.setWorkspace(null);
	}

	public Set<Schedule> getSchedules() {
		return Collections.unmodifiableSet(schedules);
	}
}
