package ua.in.sz.quartz;

import com.cronutils.Function;
import com.cronutils.model.Cron;
import com.cronutils.model.CronType;
import com.cronutils.model.SingleCron;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.field.CronField;
import com.cronutils.model.field.CronFieldName;
import com.cronutils.model.field.expression.FieldExpression;
import com.cronutils.model.field.expression.On;
import com.cronutils.model.field.expression.visitor.FieldExpressionVisitorAdaptor;
import com.cronutils.model.field.value.IntegerFieldValue;
import com.cronutils.model.field.value.SpecialChar;
import com.cronutils.model.field.value.SpecialCharFieldValue;
import com.cronutils.parser.CronParser;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class EventCronParser extends CronParser {
    private static final Map<CronFieldName, Function<CronField, CronField>> normalizers =
            ImmutableMap.<CronFieldName, Function<CronField, CronField>>builder()
                    .put(CronFieldName.DAY_OF_WEEK, EventCronParser::normalizeDayOfWeekField)
                    .build();

    public EventCronParser() {
        super(CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ));
    }

    @Override
    public Cron parse(String expression) {
        Cron cron = super.parse(expression);
        SingleCron normalizeCron = normalize(cron);
        log.debug("{} -> {}", cron.asString(), normalizeCron.asString());
        return normalizeCron;
    }

    // ================================================================================================================
    // private methods
    // ================================================================================================================

    private static SingleCron normalize(Cron cron) {
        List<CronField> fields = cron.retrieveFieldsAsMap().values().stream()
                .map(field -> normalizers.getOrDefault(field.getField(), f -> f).apply(field))
                .collect(Collectors.toList());

        return new SingleCron(cron.getCronDefinition(), fields);
    }

    private static CronField normalizeDayOfWeekField(CronField field) {
        FieldExpression expression = field.getExpression().accept(new NthNormalizeVisitor());
        return new CronField(field.getField(), expression, field.getConstraints());
    }

    private static class NthNormalizeVisitor extends FieldExpressionVisitorAdaptor {
        public FieldExpression visit(On on) {
            return new On(on.getTime(), new SpecialCharFieldValue(SpecialChar.NONE), new IntegerFieldValue(-1));
        }
    }
}
