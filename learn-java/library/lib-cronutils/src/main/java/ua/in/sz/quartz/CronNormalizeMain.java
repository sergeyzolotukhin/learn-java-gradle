package ua.in.sz.quartz;

import com.cronutils.Function;
import com.cronutils.model.Cron;
import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.field.CronField;
import com.cronutils.model.field.CronFieldName;
import com.cronutils.model.field.expression.*;
import com.cronutils.parser.CronParser;
import com.google.common.collect.ImmutableMap;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

// http://www.quartz-scheduler.org/documentation/quartz-2.3.0/tutorials/crontrigger.html
@Slf4j
public class CronNormalizeMain {
    private static final Map<Class<?>, Function<FieldExpression, FieldExpression>> convertors =
            ImmutableMap.<Class<?>, Function<FieldExpression, FieldExpression>>builder()
                    .put(And.class, CronNormalizeMain::andExpression)
                    // "#" - used to specify "the nth" XXX day of the month.
                    .put(On.class, CronNormalizeMain::onExpression)
                    .put(Between.class, CronNormalizeMain::betweenExpression)
                    // "*" - used to select all values within a field.
                    .put(Always.class, CronNormalizeMain::alwaysExpression)
                    .put(QuestionMark.class, CronNormalizeMain::questionMarkExpression)
                    // "/" - used to specify increments. For example, “0/15"
                    .put(Every.class, CronNormalizeMain::everyExpression)
                    .build();


    @SneakyThrows
    public static void main(String[] args) {
        CronDefinition cronDefinition = CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ);
        CronParser parser = new CronParser(cronDefinition);

        Cron cron = parser.parse("0 0 12 ? * 1#4,2#3");
        CronField field = cron.retrieve(CronFieldName.DAY_OF_WEEK);
        FieldExpression expression = field.getExpression();

        log.info("================================================");

        normalize(expression);

        log.info("================================================");
    }

    // ================================================================================================================
    //
    // ================================================================================================================

    private static FieldExpression normalize(FieldExpression expression) {
        return convertors.getOrDefault(expression.getClass(), CronNormalizeMain::unsupportedExpression)
                .apply(expression);
    }

    private static FieldExpression onExpression(FieldExpression expression) {
        On on = (On) expression;
        log.info("On time: {}, nth: {}", on.getTime(), on.getNth());
        return expression;
    }

    private static FieldExpression betweenExpression(FieldExpression expression) {
        Between between = (Between) expression;
        log.info("On from: {}, to: {}", between.getFrom(), between.getTo());
        return expression;
    }

    private static FieldExpression andExpression(FieldExpression expression) {
        And and = (And) expression;
        log.info("And:");
        and.getExpressions().forEach(CronNormalizeMain::normalize);
        return expression;
    }

    private static FieldExpression alwaysExpression(FieldExpression expression) {
//        Always always = (Always) expression;
        log.info("Always");
        return expression;
    }

    private static FieldExpression questionMarkExpression(FieldExpression expression) {
        log.info("QuestionMark");
        return expression;
    }

    private static FieldExpression everyExpression(FieldExpression expression) {
        Every every = (Every) expression;
        log.info("Every expression: {}, period: {}", every.getExpression(), every.getPeriod());
        normalize(every.getExpression());
        return expression;
    }

    private static FieldExpression unsupportedExpression(FieldExpression expression) {
        log.warn("Unknown expression: {}", expression);
        return expression;
    }
}
