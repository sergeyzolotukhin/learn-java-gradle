package ua.in.sz.dsl.domain;

import ua.in.sz.dsl.model.Code;

public class ScheduleTypes {
    public static final Availability AVAILABILITY = new Availability("AV");
    public static final Forecast FORECAST = new Forecast("FC");

    public static class Availability extends ScheduleType {
        public final Code RESOURCE = new ParamType("RO");
        public final Code QUANTITY = new ValueType("Q");
        public final Code PRICE = new ValueType("P");

        Availability(String code) {
            super(code);
        }
    }

    public static class Forecast extends ScheduleType {
        public final Code RESOURCE = new ValueType("RO");
        public final Code VALUE = new ValueType("Q");
        public final Code PRICE = new ValueType("P");

        Forecast(String code) {
            super(code);
        }
    }

    // ================================================================================================================

    private static class ParamType extends AbstractCode {
        private ParamType(String code) {
            super(code);
        }
    }

    private static class ValueType extends AbstractCode {
        private ValueType(String code) {
            super(code);
        }
    }

    private static abstract class ScheduleType extends AbstractCode {
        protected ScheduleType(String code) {
            super(code);
        }
    }

    private static abstract class AbstractCode implements Code {
        private final String code;

        private AbstractCode(String code) {
            this.code = code;
        }

        @Override
        public String getCode() {
            return code;
        }
    }
}
