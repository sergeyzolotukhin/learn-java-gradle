package ua.in.sz.dsl.domain;

import ua.in.sz.dsl.metadata.ParamType;
import ua.in.sz.dsl.metadata.ScheduleType;
import ua.in.sz.dsl.metadata.Type;
import ua.in.sz.dsl.metadata.ValueType;

public class Types {
    public static final Availability AVAILABILITY = new Availability("AV");
    public static final Forecast FORECAST = new Forecast("FC");

    public static class Availability extends ScheduleTypeSupport {
        public final ParamType RESOURCE = new ParamTypeSupport("RO");
        public final ValueType QUANTITY = new ValueTypeSupport("Q");
        public final ValueType PRICE = new ValueTypeSupport("P");

        Availability(String code) {
            super(code);
        }
    }

    public static class Forecast extends ScheduleTypeSupport {
        public final ParamType RESOURCE = new ParamTypeSupport("RO");
        public final ValueType VALUE = new ValueTypeSupport("Q");
        public final ValueType PRICE = new ValueTypeSupport("P");

        Forecast(String code) {
            super(code);
        }
    }

    // ================================================================================================================

    private static abstract class ScheduleTypeSupport extends TypeSupport implements ScheduleType {
        protected ScheduleTypeSupport(String code) {
            super(code);
        }
    }

    private static class ParamTypeSupport extends TypeSupport implements ParamType {
        private ParamTypeSupport(String code) {
            super(code);
        }
    }

    private static class ValueTypeSupport extends TypeSupport implements ValueType {
        private ValueTypeSupport(String code) {
            super(code);
        }
    }

    private static abstract class TypeSupport implements Type {
        private final String code;

        private TypeSupport(String code) {
            this.code = code;
        }

        @Override
        public String getCode() {
            return code;
        }
    }
}
