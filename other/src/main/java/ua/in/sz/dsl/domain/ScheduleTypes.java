package ua.in.sz.dsl.domain;

import ua.in.sz.dsl.metadata.ScheduleType;
import ua.in.sz.dsl.metadata.Type;

public class ScheduleTypes {
    public static final Availability AVAILABILITY = new Availability("AV");
    public static final Forecast FORECAST = new Forecast("FC");

    public static class Availability extends ScheduleTypeSupport {
        public final Type RESOURCE = new ParamTypeSupport("RO");
        public final Type QUANTITY = new ValueTypeSupport("Q");
        public final Type PRICE = new ValueTypeSupport("P");

        Availability(String code) {
            super(code);
        }
    }

    public static class Forecast extends ScheduleTypeSupport {
        public final Type RESOURCE = new ValueTypeSupport("RO");
        public final Type VALUE = new ValueTypeSupport("Q");
        public final Type PRICE = new ValueTypeSupport("P");

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

    private static class ParamTypeSupport extends TypeSupport {
        private ParamTypeSupport(String code) {
            super(code);
        }
    }

    private static class ValueTypeSupport extends TypeSupport {
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
