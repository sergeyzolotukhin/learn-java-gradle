package ua.in.sz.hibernate.xml.impl;

import lombok.*;
import lombok.experimental.FieldNameConstants;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Workspace {
	private Long id;
	@EqualsAndHashCode.Include
	private String name;

	@Builder.Default
	@ToString.Exclude
	protected Set<Schedule> schedules = new HashSet<>();
}
