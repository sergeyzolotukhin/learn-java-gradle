FieldConstraints
    - Set<SpecialChar> specialChars; // L # ...
    - Integer startRange; // 0
    - Integer endRange; // 59

parsers
=======================================================================================================================

CronParser
    - Map<Integer /* expression length */, List<CronParserField>> expressions
    + Cron parse(final String expression)
        List<CronParserField> fields = expressions.get(expressionLength);
        List<CronField> results = new ArrayList<>();
        for (int j = 0; j < size; j++) {
            results.add(fields.get(j)
                .parse(expressionParts[j]));
        }
        return new SingleCron(cronDefinition, results)

CronParserField
FieldParser

result of parsing model
=======================================================================================================================

SingleCron -|>
    Map<CronFieldName, CronField> fields
CronField
    FieldExpression expression;

FieldExpression
        -|> Between
        -|> And
        -|> Every
        -|> On

On
    IntegerFieldValue time;
    IntegerFieldValue nth;
    SpecialCharFieldValue specialChar;

Execution Time
=======================================================================================================================
ExecutionTime forCron(Cron cron)

SingleExecutionTime
    CronField daysOfWeekCronField;
    CronField daysOfMonthCronField;
    CronField daysOfYearCronField;
    TimeNode months;
    TimeNode hours;
    TimeNode minutes;
    TimeNode seconds;

    Optional<ZonedDateTime> nextExecution(final ZonedDateTime date)

    Optional<ZonedDateTime> lastExecution(final ZonedDateTime date)

class TimeNode {
    List<Integer> values;


