package ua.in.sz.hibernate.xml.impl;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class ScheduleValue<T> implements IdBean<Long> {
    private Long id;
    private Schedule schedule;
    private String type;
    private T value;

    @Override
    public boolean equals(Object that) {
        if (that == null || that.getClass() != getClass()) {
            return false;
        }

        final ScheduleValue<?> thatValue = (ScheduleValue<?>) that;

        return schedule != null && thatValue.schedule != null && new EqualsBuilder().
                append(schedule.getId(), thatValue.schedule.getId()).
                append(type, thatValue.type).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(schedule).append(type).toHashCode();
    }
}
