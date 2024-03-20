package ua.in.sz.tabular.domain;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@SuperBuilder
@NoArgsConstructor
public class StringScheduleValue extends ScheduleValue<String> {

    @Override
    public boolean equals(Object that) {
        return that instanceof StringScheduleValue && super.equals(that);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().appendSuper(super.hashCode()).toHashCode();
    }
}