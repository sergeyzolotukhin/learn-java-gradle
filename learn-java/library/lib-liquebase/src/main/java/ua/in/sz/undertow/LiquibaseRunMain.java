package ua.in.sz.undertow;

import liquibase.command.CommandScope;
import liquibase.command.core.UpdateCommandStep;
import liquibase.command.core.helpers.DbUrlConnectionCommandStep;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class LiquibaseRunMain {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost/postgres";
        String user = "postgres";
        String password = "postgres";
        String changeLogFile = "changelog-runtime.yaml";

        Connection c = null;
        try {
            c = DriverManager.getConnection(url, user, password);

            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(c));

            new CommandScope(UpdateCommandStep.COMMAND_NAME)
                    .addArgumentValue(DbUrlConnectionCommandStep.DATABASE_ARG, database)
                    .addArgumentValue(UpdateCommandStep.CHANGELOG_FILE_ARG, changeLogFile)
                    .execute();

        } catch (SQLException | LiquibaseException e) {
            log.error("Error: ", e);
        } finally {
            if (c != null) {
                try {
                    c.rollback();
                    c.close();
                } catch (SQLException ignore) {

                }
            }
        }
    }
}
