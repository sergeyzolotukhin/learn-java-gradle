package ua.in.sz.hibernate.xml.impl;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class Workspace implements IdBean<Long> {
	private Long id;
	private String name;

	@Builder.Default
	private List<Schedule> schedules = new ArrayList<>();

	@Override
	public Long getId() {
		return id;
	}
}
