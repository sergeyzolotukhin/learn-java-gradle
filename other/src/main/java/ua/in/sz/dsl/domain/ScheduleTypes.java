package ua.in.sz.dsl.domain;

import ua.in.sz.dsl.model.Type;

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

    private static class ParamType extends AbstractType {
        private ParamType(String code) {
            super(code);
        }
    }

    private static class ValueType extends AbstractType {
        private ValueType(String code) {
            super(code);
        }
    }

    private static abstract class ScheduleType extends AbstractType {
        protected ScheduleType(String code) {
            super(code);
        }
    }

    private static abstract class AbstractType implements Type {
        private final String code;

        private AbstractType(String code) {
            this.code = code;
        }

        @Override
        public String getCode() {
            return code;
        }
    }
}
