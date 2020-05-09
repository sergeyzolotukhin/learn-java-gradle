package ua.in.sz.hibernate.xml.impl;

import lombok.*;
import lombok.experimental.FieldNameConstants;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Schedule {
	private Long id;
	@EqualsAndHashCode.Include
	private String name;
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
