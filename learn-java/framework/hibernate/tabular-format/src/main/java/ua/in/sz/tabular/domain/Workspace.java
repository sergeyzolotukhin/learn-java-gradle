package ua.in.sz.tabular.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
