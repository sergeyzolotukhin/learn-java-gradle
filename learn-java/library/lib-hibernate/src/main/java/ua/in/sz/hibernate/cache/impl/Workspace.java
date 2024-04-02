package ua.in.sz.hibernate.cache.impl;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

	@OneToMany(mappedBy = "workspace", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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
