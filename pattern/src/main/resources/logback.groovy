import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ua.in.sz.logging.logs.AddTaskConverter
import ua.in.sz.logging.logs.MdcFilter

import static ch.qos.logback.core.spi.FilterReply.*

scan("30 seconds")

final String LOG_PATTERN = "%d{HH:mm:ss.SSS} [%-20.20thread] %-5level | %-9.9X{feature} | %-40.40logger{36} - %highlightex(%msg){add tasks} %n"

conversionRule("highlightex", AddTaskConverter)
appender("CONSOLE", ConsoleAppender) {
    filter(MdcFilter) { feature = 'HBM-00001'; onMatch = NEUTRAL; onMismatch = DENY }
    encoder(PatternLayoutEncoder) { pattern = LOG_PATTERN }
}

logger("ua.in.sz.logging.tasks.SecurityDecorator", WARN, ["CONSOLE"])
logger("ua.in.sz.logging.tasks.DebugDecorator", WARN, ["CONSOLE"])

root(DEBUG, ["CONSOLE"])