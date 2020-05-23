package ua.in.sz.tabular.domain;

import lombok.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Schedule implements IdBean<Long> {
	private Long id;
	private String type;
	private String identification;
	private LocalDateTime startDate;
	private LocalDateTime stopDate;

	private Workspace workspace;

	@Builder.Default
	private List<NumberScheduleValue> numberValueList = new ArrayList<>();
	@Builder.Default
	private List<StringScheduleValue> stringValueList = new ArrayList<>();

	@Builder.Default
	private Set<NumberScheduleValue> numberValueSet = new HashSet<>();
	@Builder.Default
	private Set<StringScheduleValue> stringValueSet = new HashSet<>();

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(startDate).append(type).append(identification).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != getClass()) {
			return false;
		}

		final Schedule that = (Schedule) obj;

		return new EqualsBuilder().
				append(startDate, that.startDate).
				append(type, that.type).
				append(identification, that.identification).isEquals();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).
				append("type", type).
				append("identification", identification).
				append("startDate", startDate)
				.toString();
	}

}
