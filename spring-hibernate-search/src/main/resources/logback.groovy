import ch.qos.logback.classic.encoder.PatternLayoutEncoder

scan("30 seconds")

final String LOG_PATTERN = "%d{HH:mm:ss.SSS} [%-20.20thread] %-5level | %-9.9X{feature} | %-40.40logger{36} - %msg %n"
final String CONSOLE = "CONSOLE"


appender(CONSOLE, ConsoleAppender) {
    encoder(PatternLayoutEncoder) { pattern = LOG_PATTERN }
}

logger("org.springframework", WARN, [CONSOLE])
logger("org.hibernate", WARN, [CONSOLE])
logger("com.zaxxer", WARN, [CONSOLE])

root(INFO, [CONSOLE])