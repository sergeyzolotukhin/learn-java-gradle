package ua.in.sz.quartz;

import com.cronutils.model.Cron;
import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.field.CronField;
import com.cronutils.model.field.CronFieldName;
import com.cronutils.model.field.expression.And;
import com.cronutils.model.field.expression.Between;
import com.cronutils.model.field.expression.FieldExpression;
import com.cronutils.model.field.expression.On;
import com.cronutils.model.field.expression.visitor.FieldExpressionVisitorAdaptor;
import com.cronutils.parser.CronParser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CronVisitorMain {
    @SneakyThrows
    public static void main(String[] args) {
        CronDefinition cronDefinition = CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ);
        CronParser parser = new CronParser(cronDefinition);

        Cron cron = parser.parse("0 0 12 * * ?");
        CronField field = cron.retrieve(CronFieldName.DAY_OF_WEEK);
        FieldExpression expression = field.getExpression();

        log.info("================================================");

        FieldExpressionVisitorAdaptor visitor = new LogVisitor();
        expression.accept(visitor);

        log.info("================================================");
    }

    private static class LogVisitor extends FieldExpressionVisitorAdaptor {
        @Override
        public FieldExpression visit(On on) {
            log.info("On time: {}, nth: {}", on.getTime(), on.getNth());
            return super.visit(on);
        }

        @Override
        public FieldExpression visit(And and) {
            log.info("And:");
            and.getExpressions().forEach(e -> e.accept(this));
            return super.visit(and);
        }

        @Override
        public FieldExpression visit(Between between) {
            log.info("Between time: {}, nth: {}", between.getFrom(), between.getTo());
            return super.visit(between);
        }
    }
}
