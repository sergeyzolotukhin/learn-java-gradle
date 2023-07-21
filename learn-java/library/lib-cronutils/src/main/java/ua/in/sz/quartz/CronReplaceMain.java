package ua.in.sz.quartz;

import com.cronutils.model.Cron;
import com.cronutils.model.CronType;
import com.cronutils.model.SingleCron;
import com.cronutils.model.definition.CronDefinition;
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
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class CronReplaceMain {
    @SneakyThrows
    public static void main(String[] args) {
        CronDefinition cronDefinition = CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ);
        CronParser parser = new CronParser(cronDefinition);

        Cron cron = parser.parse("0 0 12 ? * 1#4");
        SingleCron normalizedCron = normalize(cron);

        log.info("{} -> {}", cron.asString(), normalizedCron.asString());
    }

    private static SingleCron normalize(Cron cron) {
        List<CronField> fields = cron.retrieveFieldsAsMap().values().stream()
                .map(CronReplaceMain::normalizeField)
                .collect(Collectors.toList());

        return new SingleCron(cron.getCronDefinition(), fields);
    }

    private static CronField normalizeField(CronField field) {
        if (CronFieldName.DAY_OF_WEEK.equals(field.getField())) {
            return normalizeDayOfWeekField(field);
        } else {
            return field;
        }
    }

    private static CronField normalizeDayOfWeekField(CronField field) {
        if (!CronFieldName.DAY_OF_WEEK.equals(field.getField())) {
            throw new IllegalArgumentException(String.format("Unsupported field type %s", field.getField()));
        }

        FieldExpression expression = field.getExpression().accept(new NthNormalizeVisitor());
        return new CronField(field.getField(), expression, field.getConstraints());
    }

    private static class NthNormalizeVisitor extends FieldExpressionVisitorAdaptor {
        public FieldExpression visit(On on) {
            return new On(on.getTime(), new SpecialCharFieldValue(SpecialChar.NONE), new IntegerFieldValue(-1));
        }
    }
}
