package ua.in.sz.quartz;

import com.cronutils.Function;
import com.cronutils.model.Cron;
import com.cronutils.model.SingleCron;
import com.cronutils.model.definition.CronConstraint;
import com.cronutils.model.definition.CronConstraintsFactory;
import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.field.CronField;
import com.cronutils.model.field.CronFieldName;
import com.cronutils.model.field.expression.And;
import com.cronutils.model.field.expression.FieldExpression;
import com.cronutils.model.field.expression.On;
import com.cronutils.model.field.expression.visitor.FieldExpressionVisitorAdaptor;
import com.cronutils.model.field.value.IntegerFieldValue;
import com.cronutils.model.field.value.SpecialChar;
import com.cronutils.model.field.value.SpecialCharFieldValue;
import com.cronutils.parser.CronParser;
import com.google.common.collect.ImmutableMap;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class EventCronParser extends CronParser {
    private static final Map<CronFieldName, Function<CronField, CronField>> translators =
            ImmutableMap.<CronFieldName, Function<CronField, CronField>>builder()
                    .put(CronFieldName.DAY_OF_WEEK, EventCronParser::translateDayOfWeekField)
                    .build();

    public EventCronParser() {
        super(cronDefinition());
    }

    @Override
    public Cron parse(String expression) {
        Cron origenCron = super.parse(expression);
        Cron resultCron = translate(origenCron);
        log.info("Cron expression [{}] is translated to [{}]", origenCron.asString(), resultCron.asString());
        return resultCron;
    }

    // ================================================================================================================
    // private methods for expression validation
    // ================================================================================================================

    private static CronDefinition cronDefinition() {
        return CronDefinitionBuilder.defineCron()
                .withSeconds().withValidRange(0, 59).and()
                .withMinutes().withValidRange(0, 59).and()
                .withHours().withValidRange(0, 23).and()
                .withDayOfMonth().withValidRange(1, 31).supportsL().supportsW().supportsLW().supportsQuestionMark().and()
                .withMonth().withValidRange(1, 12).and()
                .withDayOfWeek().withValidRange(1, 7).withMondayDoWValue(2).supportsHash().supportsL().supportsQuestionMark().and()
                .withYear().withValidRange(1970, 2099).withStrictRange().optional().and()
                .withCronValidation(CronConstraintsFactory.ensureEitherDayOfWeekOrDayOfMonth())
                .withCronValidation(new ListOfDayOfWeekCronConstraint())
                .instance();
    }

    private static class ListOfDayOfWeekCronConstraint extends CronConstraint {
        public ListOfDayOfWeekCronConstraint() {
            super("A day-of-week does not support ','");
        }

        @Override
        public boolean validate(Cron cron) {
            FieldExpression expression = cron.retrieve(CronFieldName.DAY_OF_WEEK).getExpression();
            return !(expression instanceof And);
        }
    }

    // ================================================================================================================
    // private methods for expression normalization
    // ================================================================================================================

    private static SingleCron translate(Cron cron) {
        List<CronField> fields = cron.retrieveFieldsAsMap().values().stream()
                .map(field -> translators.getOrDefault(field.getField(), f -> f).apply(field))
                .collect(Collectors.toList());

        return new SingleCron(cron.getCronDefinition(), fields);
    }

    private static CronField translateDayOfWeekField(CronField field) {
        FieldExpression expression = field.getExpression().accept(new NthTranslatorVisitor());
        return new CronField(field.getField(), expression, field.getConstraints());
    }

    private static class NthTranslatorVisitor extends FieldExpressionVisitorAdaptor {
        public FieldExpression visit(On on) {
            return new RelativeOn(on.getTime(), on.getNth());
        }
    }

    @Getter
    private static class RelativeOn extends On {
        private final IntegerFieldValue relativeNth;
        public RelativeOn(IntegerFieldValue time, IntegerFieldValue relativeNth) {
            super(time, new SpecialCharFieldValue(SpecialChar.NONE), new IntegerFieldValue(-1));
            this.relativeNth = relativeNth;
        }
    }
}
