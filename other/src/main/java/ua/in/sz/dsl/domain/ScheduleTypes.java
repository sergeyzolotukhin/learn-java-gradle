package ua.in.sz.dsl.domain;

import ua.in.sz.dsl.metadata.Type;

public class ScheduleTypes {
    public static final Availability AVAILABILITY = new Availability("AV");
    public static final Forecast FORECAST = new Forecast("FC");

    public static class Availability extends ScheduleType {
        public final Type RESOURCE = new ParamType("RO");
        public final Type QUANTITY = new ValueType("Q");
        public final Type PRICE = new ValueType("P");

        Availability(String code) {
            super(code);
        }
    }

    public static class Forecast extends ScheduleType {
        public final Type RESOURCE = new ValueType("RO");
        public final Type VALUE = new ValueType("Q");
        public final Type PRICE = new ValueType("P");

        Forecast(String code) {
            super(code);
        }
    }

    // ================================================================================================================

    private static class ParamType extends TypeSupport {
        private ParamType(String code) {
            super(code);
        }
    }

    private static class ValueType extends TypeSupport {
        private ValueType(String code) {
            super(code);
        }
    }

    private static abstract class ScheduleType extends TypeSupport {
        protected ScheduleType(String code) {
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
