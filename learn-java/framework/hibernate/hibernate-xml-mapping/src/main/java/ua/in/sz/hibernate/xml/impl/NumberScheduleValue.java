package ua.in.sz.hibernate.xml.impl;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.math.BigDecimal;

@SuperBuilder
@NoArgsConstructor
public class NumberScheduleValue extends ScheduleValue<BigDecimal> {

    @Override
    public boolean equals(Object that) {
        return that instanceof NumberScheduleValue && super.equals(that);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().appendSuper(super.hashCode()).toHashCode();
    }
}
